package com.ricky.healthifier.service.food;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.food.FoodDTOTransformer;
import com.ricky.healthifier.entity.food.QuantityEnumDTO;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDAO foodDAO;

    private final Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);
    private final FoodDTOTransformer foodDTOTransformer = new FoodDTOTransformer();
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
            Food food = foodDTOTransformer.transformToModel(foodDTOListIterator.next());
            foodList.add(food);
        }

        logger.info("Service: Success Fetching All Foods");

        // Return list of food
        return foodList;
    }
}