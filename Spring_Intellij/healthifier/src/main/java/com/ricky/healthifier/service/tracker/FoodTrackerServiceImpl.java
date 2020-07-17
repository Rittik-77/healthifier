package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.dao.FoodTrackerDAO;
import com.ricky.healthifier.dao.UserDAO;
import com.ricky.healthifier.datamodel.tracker.FoodTracker;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.tracker.FoodTrackerDTO;
import com.ricky.healthifier.entity.tracker.FoodTrackerDTOTransformer;
import com.ricky.healthifier.entity.user.UserDTO;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class FoodTrackerServiceImpl implements FoodTrackerService {

    @Autowired
    private FoodTrackerDAO foodTrackerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FoodDAO foodDAO;

    private final Logger logger = LoggerFactory.getLogger(FoodTrackerServiceImpl.class);
    private final FoodTrackerDTOTransformer transformer = new FoodTrackerDTOTransformer();

    @Override
    public boolean addFoodToTracker(FoodTracker foodTracker) throws AppException {

        logger.info("Service: Add food to tracker");

        // Verify the payload
        BaseValidator.checkObjectIsNull(foodTracker.getId(), "Id should be null");
        BaseValidator.checkObjectIsNotNull(foodTracker.getFoodName(), "Food Name cannot be null");
        BaseValidator.checkObjectIsNull(foodTracker.getDate(), "Date should be null");
        BaseValidator.checkObjectIsNotNull(foodTracker.getAmount(), "Amount can not be null");
        // TODO: make email null and will be calculated from token
        BaseValidator.checkObjectIsNotNull(foodTracker.getEmail(), "User Email cannot be null");

        // Verify that user exists in the Database
        Optional<UserDTO> userDTOByEmail = userDAO.findById(foodTracker.getEmail());
        if(userDTOByEmail.isEmpty()) {
            throw new AppException("User does not exists in the database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Verify that food exists in the Database
        Optional<FoodDTO> foodDTOByName = foodDAO.findById(foodTracker.getFoodName());
        if(foodDTOByName.isEmpty()) {
            throw new AppException("Food not present in Database", ExceptionLevel.VALIDATION, "Malicious attack. Alert!");
        }

        // Set the Date
        foodTracker.setDate(new Date(System.currentTimeMillis()));

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
}
