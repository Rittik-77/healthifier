package com.ricky.healthifier.controller.tracker;

import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;
import com.ricky.healthifier.service.tracker.WorkoutTrackerService;
import com.ricky.healthifier.utils.commons.BaseConstants;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workoutTrackers")
@CrossOrigin(BaseConstants.APP_LINK)
public class WorkoutTrackerController {

    @Autowired
    private WorkoutTrackerService workoutTrackerService;

    private final Logger logger = LoggerFactory.getLogger(WorkoutTrackerController.class);
    private final WorkoutTrackerVOTransformer transformer = new WorkoutTrackerVOTransformer();

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addWorkoutToTracker(@RequestBody WorkoutTrackerVO workoutTrackerVO) throws AppException {

        logger.info("Rest: Adding Workout to Tracker");

        // TODO: Validate the token

        // Validate the payload
        BaseValidator.checkObjectIsNotNull(workoutTrackerVO, "Payload should not be null");

        // Transform to model
        WorkoutTracker workoutTracker = transformer.transformToModel(workoutTrackerVO);

        // Call the service
        return workoutTrackerService.addWorkoutToTracker(workoutTracker);
    }
}
