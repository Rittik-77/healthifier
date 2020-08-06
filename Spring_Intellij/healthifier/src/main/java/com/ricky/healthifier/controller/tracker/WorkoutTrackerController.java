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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workoutTrackers")
@CrossOrigin(BaseConstants.APP_LINK)
public class WorkoutTrackerController {

    @Autowired
    private WorkoutTrackerService workoutTrackerService;

    private final Logger logger = LoggerFactory.getLogger(WorkoutTrackerController.class);
    private final WorkoutTrackerVOTransformer transformer = new WorkoutTrackerVOTransformer();

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addWorkoutToTracker(@RequestBody WorkoutTrackerVO workoutTrackerVO,
                                       @RequestHeader Map<String, String> headers) throws AppException {

        logger.info("Rest: Adding Workout to Tracker");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Validate the payload
        BaseValidator.checkObjectIsNotNull(workoutTrackerVO, "Payload should not be null");

        // Transform to model
        WorkoutTracker workoutTracker = transformer.transformToModel(workoutTrackerVO);

        // Call the service
        return workoutTrackerService.addWorkoutToTracker(workoutTracker, token);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkoutTrackerVO> getWorkoutTracker(@RequestHeader Map<String, String> headers)
                                                    throws AppException {

        logger.info("Rest: Fetching Workout Tracker");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Call the service
        List<WorkoutTracker> workoutTrackerList = workoutTrackerService.getWorkoutTracker(token);

        // Transform to VO
        List<WorkoutTrackerVO> workoutTrackerVOList = new ArrayList<>();
        for (WorkoutTracker workoutTracker : workoutTrackerList) {
            workoutTrackerVOList.add(transformer.transformToVO(workoutTracker));
        }

        return workoutTrackerVOList;
    }

    @RequestMapping(value = "{workoutTrackerId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateWorkoutInTracker(@RequestHeader Map<String, String> headers, @PathVariable Integer workoutTrackerId,
                                          @RequestBody WorkoutTrackerVO workoutTrackerVO) throws AppException {

        logger.info("Rest: Update Workout in Tracker");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Validate the payload and path param
        BaseValidator.checkObjectIsNotNull(workoutTrackerVO, "Payload should not be null");
        BaseValidator.checkObjectIsNotNull(workoutTrackerId, "Workout Tracker Id should not be null");

        // Transform to model
        WorkoutTracker workoutTracker = transformer.transformToModel(workoutTrackerVO);

        // Call the service
        return workoutTrackerService.updateWorkoutInTracker(workoutTrackerId, workoutTracker, token);
    }

    @RequestMapping(value = "{workoutTrackerId}",method = RequestMethod.DELETE)
    public boolean deleteWorkoutFromTracker(@PathVariable Integer workoutTrackerId,
                                         @RequestHeader Map<String, String> headers) throws AppException {

        logger.info("Rest: Delete Workout from Tracker");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Validate the path param
        BaseValidator.checkObjectIsNotNull(workoutTrackerId, "Workout Tracker Id should not be null");

        // Call the service
        return workoutTrackerService.deleteWorkoutFromTracker(workoutTrackerId, token);
    }
}