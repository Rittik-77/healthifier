package com.ricky.healthifier.controller.food;

import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.service.food.FoodService;
import com.ricky.healthifier.utils.commons.BaseConstants;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin(BaseConstants.APP_LINK)
public class FoodController {

    @Autowired
    private FoodService foodService;

    private final Logger logger = LoggerFactory.getLogger(FoodController.class);
    private final FoodVOTransformer transformer = new FoodVOTransformer();

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FoodVO> getAllFood() throws AppException {

        logger.info("Rest: Fetch all Foods");

        // Retrieve food list
        List<Food> foodList = foodService.getAllFoods();

        // Transform the list
        ListIterator<Food> foodListIterator = foodList.listIterator();
        List<FoodVO> foodVOList = new ArrayList<>();
        while(foodListIterator.hasNext()) {
            FoodVO foodVO = transformer.transformToVO(foodListIterator.next());
            foodVOList.add(foodVO);
        }

        // Return the list
        return foodVOList;
    }

    @RequestMapping(value = "{foodName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodVO getFoodByName(@PathVariable String foodName) throws AppException {

        logger.info("Rest: Fetch food by name");
        BaseValidator.checkObjectIsNotNull(foodName, "Food Name should not be null");
        Food food = foodService.getFoodByName(foodName);
        FoodVO foodVO = transformer.transformToVO(food);
        return foodVO;
    }
}
