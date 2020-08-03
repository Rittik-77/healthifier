package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.dao.FoodTrackerDAO;
import com.ricky.healthifier.dao.UserDAO;
import com.ricky.healthifier.datamodel.tracker.FoodTracker;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.tracker.FoodTrackerDTO;
import com.ricky.healthifier.entity.tracker.FoodTrackerDTOTransformer;
import com.ricky.healthifier.entity.user.UserDTO;
import com.ricky.healthifier.service.auth.JwtService;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodTrackerServiceImpl implements FoodTrackerService {

    @Autowired
    private FoodTrackerDAO foodTrackerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FoodDAO foodDAO;

    @Autowired
    private JwtService jwtService;

    private final Logger logger = LoggerFactory.getLogger(FoodTrackerServiceImpl.class);
    private final FoodTrackerDTOTransformer transformer = new FoodTrackerDTOTransformer();

    @Override
    public boolean addFoodToTracker(FoodTracker foodTracker, String token) throws AppException {

        logger.info("Service: Add food to tracker");

        // Verify the payload
        BaseValidator.checkObjectIsNull(foodTracker.getId(), "Id should be null");
        BaseValidator.checkObjectIsNotNull(foodTracker.getFoodName(), "Food Name cannot be null");
        BaseValidator.checkObjectIsNotNull(foodTracker.getDate(), "Date should not be null");
        BaseValidator.checkObjectIsNotNull(foodTracker.getAmount(), "Amount can not be null");
        BaseValidator.checkObjectIsNull(foodTracker.getEmail(), "User Email should be null");

        // Calculate email from token
        String email = jwtService.extractEmail(token);
        BaseValidator.checkObjectIsNotNull(email, "Token invalid");

        // Verify that user exists in the Database
        Optional<UserDTO> userDTOByEmail = userDAO.findById(email);
        if(userDTOByEmail.isEmpty()) {
            throw new AppException("User does not exists in the database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Verify that food exists in the Database
        Optional<FoodDTO> foodDTOByName = foodDAO.findById(foodTracker.getFoodName());
        if(foodDTOByName.isEmpty()) {
            throw new AppException("Food not present in Database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Transform to DTO
        FoodTrackerDTO foodTrackerDTO = transformer.transformToDTO(foodTracker);

        // Food Name and Email has to be converted here as it depends on the services
        foodTrackerDTO.setFoodDTO(foodDTOByName.get());
        foodTrackerDTO.setUserDTO(userDTOByEmail.get());

        // Save to DataBase
        foodTrackerDAO.saveAndFlush(foodTrackerDTO);
        logger.info("Service: Food added successfully to tracker");

        return true;
    }

    @Override
    public List<FoodTracker> getFoodTracker(String token) throws AppException {

        logger.info("Service: Fetch Food Tracking list");
        List<FoodTracker> foodTrackerList = new ArrayList<>();

        // Find email from token
        String email = jwtService.extractEmail(token);

        // Find and filter the food tracker items
        List<FoodTrackerDTO> foodTrackerDTOList = foodTrackerDAO.findAll();
        List<FoodTrackerDTO> foodTrackerDTOListForLoggedUser = foodTrackerDTOList.stream()
                .filter(dto -> dto.getUserDTO().getEmail().equals(email)).collect(Collectors.toList());

        if(foodTrackerDTOListForLoggedUser.isEmpty()) {
            throw new AppException("Your food list is empty", ExceptionLevel.INFO, "No foods added by user in tracker");
        }

        // Transform to model
        for (FoodTrackerDTO foodTrackerDTO : foodTrackerDTOListForLoggedUser)
            foodTrackerList.add(transformer.transformToModel(foodTrackerDTO));

        logger.info("Service: Success fetching food tracker");
        return foodTrackerList;
    }

}
