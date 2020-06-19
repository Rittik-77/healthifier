package com.ricky.healthifier.controller.food;

import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.service.food.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    private final Logger logger = LoggerFactory.getLogger(FoodController.class);
    private final FoodVOTransformer foodVOTransformer = new FoodVOTransformer();

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FoodVO> getAllFood() {

        logger.info("Rest: Fetch all Foods");

        // Retrieve food list
        List<Food> foodList = foodService.getAllFoods();

        // Transform the list
        ListIterator<Food> foodListIterator = foodList.listIterator();
        List<FoodVO> foodVOList = new ArrayList<FoodVO>();
        while(foodListIterator.hasNext()) {
            FoodVO foodVO = foodVOTransformer.transformToVO(foodListIterator.next());
            foodVOList.add(foodVO);
        }

        //Return the list
        return foodVOList;
    }
}
