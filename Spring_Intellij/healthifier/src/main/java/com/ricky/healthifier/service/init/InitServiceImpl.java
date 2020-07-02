package com.ricky.healthifier.service.init;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.dao.WorkoutDAO;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.food.QuantityEnumDTO;
import com.ricky.healthifier.entity.workout.WorkoutDTO;
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

    @Autowired
    private WorkoutDAO workoutDAO;

    private final Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);

    @Override
    public boolean initializeDBs() throws AppException {

        logger.info("Populating DB with initial values");

        JSONParser jsonParser = new JSONParser();
        String foodJsonFilePath = "D:\\CodeRepo\\PROJECTS\\GitHubProjects\\HealthifierApp\\Spring_Intellij\\healthifier\\src\\main\\resources\\dbFeeder\\foodDB.json";
        String workoutJsonFilePath = "D:\\CodeRepo\\PROJECTS\\GitHubProjects\\HealthifierApp\\Spring_Intellij\\healthifier\\src\\main\\resources\\dbFeeder\\workoutDB.json";
        FileReader foodJsonFile;
        FileReader workoutJsonFile;
        try {
            foodJsonFile = new FileReader(new File(foodJsonFilePath));
            workoutJsonFile = new FileReader(new File(workoutJsonFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new AppException("DB Feeder file not found");
        }

        JSONArray foodJsonArray;
        JSONArray workoutJsonArray;
        try {
            foodJsonArray = (JSONArray)jsonParser.parse(foodJsonFile);
            workoutJsonArray = (JSONArray)jsonParser.parse(workoutJsonFile);
        } catch (IOException e) {
            throw new AppException("Error reading json file");
        } catch (ParseException e) {
            throw new AppException("Error parsing json file");
        }

        Iterator<JSONObject> foodDataIterator = foodJsonArray.iterator();
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

        Iterator<JSONObject> workoutDataIterator = workoutJsonArray.iterator();
        while (workoutDataIterator.hasNext()) {
            JSONObject workoutData = workoutDataIterator.next();
            String name = workoutData.get("name").toString();
            double calories = Double.parseDouble(workoutData.get("calories_per_hour").toString());

            WorkoutDTO workoutDTO = new WorkoutDTO(name, calories);
            workoutDAO.saveAndFlush(workoutDTO);
        }

        return true;
    }

    @Override
    public boolean deleteDBsAtExit() {
        foodDAO.deleteAll();
        workoutDAO.deleteAll();
        return true;
    }
}
