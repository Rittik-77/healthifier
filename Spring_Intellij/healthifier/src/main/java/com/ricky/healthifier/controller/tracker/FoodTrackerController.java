package com.ricky.healthifier.controller.tracker;

import com.ricky.healthifier.datamodel.tracker.FoodTracker;
import com.ricky.healthifier.service.tracker.FoodTrackerService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/foodTrackers")
@CrossOrigin(BaseConstants.APP_LINK)
public class FoodTrackerController {

    @Autowired
    private FoodTrackerService foodTrackerService;

    private final Logger logger= LoggerFactory.getLogger(FoodTrackerController.class);
    private final FoodTrackerVOTransformer transformer = new FoodTrackerVOTransformer();

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addFoodToTracker(@RequestBody FoodTrackerVO foodTrackerVO,
                                    @RequestHeader Map<String, String> headers) throws AppException {

        logger.info("Rest: Add Food To Tracker");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Validate the payload
        BaseValidator.checkObjectIsNotNull(foodTrackerVO, "Payload should not be null");

        // Transform to model
        FoodTracker foodTracker = transformer.transformToModel(foodTrackerVO);

        // Call the service
        return foodTrackerService.addFoodToTracker(foodTracker, token);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FoodTrackerVO> getFoodTracker(@RequestHeader Map<String, String> headers) throws AppException {

        logger.info("Rest: Fetching Food Tracker");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Call the service
        List<FoodTracker> foodTrackerList = foodTrackerService.getFoodTracker(token);

        // Transform to VO
        List<FoodTrackerVO> foodTrackerVOList = new ArrayList<>();
        for (FoodTracker foodTracker : foodTrackerList) {
            foodTrackerVOList.add(transformer.transformToVO(foodTracker));
        }

        return foodTrackerVOList;
    }

    @RequestMapping(value = "{foodTrackerId}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateFoodInTracker(@RequestBody FoodTrackerVO foodTrackerVO, @PathVariable Integer foodTrackerId,
                                       @RequestHeader Map<String, String> headers) throws AppException {

        logger.info("Rest: Update Food in Tracker");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Validate the payload and path param
        BaseValidator.checkObjectIsNotNull(foodTrackerVO, "Payload should not be null");
        BaseValidator.checkObjectIsNotNull(foodTrackerId, "Food Tracker Id should not be null");

        // Transform to model
        FoodTracker foodTracker = transformer.transformToModel(foodTrackerVO);

        // Call the service
        return foodTrackerService.updateFoodInTracker(foodTrackerId, foodTracker, token);
    }

    @RequestMapping(value = "{foodTrackerId}",method = RequestMethod.DELETE)
    public boolean deleteFoodFromTracker(@PathVariable Integer foodTrackerId,
                                         @RequestHeader Map<String, String> headers) throws AppException {

        logger.info("Rest: Delete Food from Tracker");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Validate the payload
        BaseValidator.checkObjectIsNotNull(foodTrackerId, "Food Tracker Id should not be null");

        // Call the service
        return foodTrackerService.deleteFoodFromTracker(foodTrackerId, token);
    }

}
