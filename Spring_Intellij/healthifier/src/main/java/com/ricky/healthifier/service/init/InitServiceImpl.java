package com.ricky.healthifier.service.init;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.food.QuantityEnumDTO;
import com.ricky.healthifier.utils.exception.AppException;
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
import java.util.Iterator;

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private FoodDAO foodDAO;

    private final Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);

    @Override
    public boolean initializeFoodDB() throws AppException {

        logger.info("Populating Food DB with initial values");

        JSONParser jsonParser = new JSONParser();
        String jsonFilePath = "D:\\CodeRepo\\PROJECTS\\GitHubProjects\\HealthifierApp\\Spring_Intellij\\healthifier\\src\\main\\resources\\dbFeeder\\foodDB.json";
        FileReader file = null;
        try {
            file = new FileReader(new File(jsonFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new AppException("DB Feeder file not found");
        }

        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray)jsonParser.parse(file);
        } catch (IOException e) {
            throw new AppException("Error reading json file");
        } catch (ParseException e) {
            throw new AppException("Error parsing json file");
        }

        Iterator<JSONObject> foodDataIterator = jsonArray.iterator();
        while(foodDataIterator.hasNext()) {
            JSONObject foodData = foodDataIterator.next();
            String name = foodData.get("name").toString();
            String qty_enum = foodData.get("qty_enum").toString();
            double qty = Double.parseDouble(foodData.get("qty").toString());
            double calories = Double.parseDouble(foodData.get("calories").toString());
            String image_url = foodData.get("image_url").toString();

            FoodDTO foodDTO = new FoodDTO(name, new QuantityEnumDTO(qty_enum), qty, calories, image_url);
            foodDAO.saveAndFlush(foodDTO);
        }
        return true;
    }

    @Override
    public boolean deleteFoodDBAtExit() {
        foodDAO.deleteAll();
        return true;
    }
}
