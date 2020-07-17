package com.ricky.healthifier.controller.tracker;

import com.ricky.healthifier.datamodel.tracker.FoodTracker;
import com.ricky.healthifier.service.tracker.FoodTrackerService;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/foodTrackers")
public class FoodTrackerController {

    @Autowired
    private FoodTrackerService foodTrackerService;

    private final Logger logger= LoggerFactory.getLogger(FoodTrackerController.class);
    private final FoodTrackerVOTransformer transformer = new FoodTrackerVOTransformer();

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addFoodToTracker(@RequestBody FoodTrackerVO foodTrackerVO) throws AppException {

        logger.info("Rest: Add Food To Tracker");

        // TODO: Validate the token

        // Validate the payload
        BaseValidator.checkObjectIsNotNull(foodTrackerVO, "Payload should not be null");

        // Transform to model
        FoodTracker foodTracker = transformer.transformToModel(foodTrackerVO);

        // Call the service
        return foodTrackerService.addFoodToTracker(foodTracker);
    }
}
