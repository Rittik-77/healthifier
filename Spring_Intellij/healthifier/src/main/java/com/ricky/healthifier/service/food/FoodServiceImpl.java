package com.ricky.healthifier.service.food;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.food.FoodDTOTransformer;
import com.ricky.healthifier.entity.food.QuantityEnumDTO;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDAO foodDAO;

    private final Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);
    private final FoodDTOTransformer transformer = new FoodDTOTransformer();
    private final QuantityEnumDTO quantityEnumDTO = new QuantityEnumDTO();

    @Override
    public List<Food> getAllFoods() throws AppException {

        logger.info("Service: Fetch all Foods");

        // Retrieve the list from Database
        List<FoodDTO> foodDTOList = foodDAO.findAll();

        // If list is empty, Throw an exception with UI Message
        if(foodDTOList.isEmpty()) {
            throw new AppException("Food List is Empty", ExceptionLevel.INFO, "Food DataBase is Empty");
        }

        // Transform the list
        ListIterator<FoodDTO> foodDTOListIterator = foodDTOList.listIterator();
        List<Food> foodList = new ArrayList<>();
        while(foodDTOListIterator.hasNext()) {
            Food food = transformer.transformToModel(foodDTOListIterator.next());
            foodList.add(food);
        }

        logger.info("Service: Success Fetching All Foods");

        // Return list of food
        return foodList;
    }

    @Override
    public Food getFoodByName(String foodName) throws AppException {

        logger.info("Service: Start Fetching Food By its Name");

        // Retrieve from Database
        FoodDTO foodDTO = foodDAO.findById(foodName).orElse(null);
        BaseValidator.checkObjectIsNotNull(foodDTO, "Food with requested name not found");

        // Convert to Model
        Food food = transformer.transformToModel(foodDTO);

        logger.info("Service: Success Fetching Food By Name");
        return food;
    }
}