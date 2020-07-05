package com.ricky.healthifier.controller.workout;

import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.datamodel.workout.Workout;
import com.ricky.healthifier.service.workout.WorkoutService;
import com.ricky.healthifier.utils.exception.AppException;
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
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    private final Logger logger = LoggerFactory.getLogger(WorkoutController.class);
    private final WorkoutVOTransformer transformer = new WorkoutVOTransformer();

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkoutVO> getAllWorkouts() throws AppException {

        logger.info("Rest: Fetch all Workouts");

        // Retrieve food list
        List<Workout> workoutList = workoutService.getAllWorkouts();

        // Transform the list
        ListIterator<Workout> workoutListIterator = workoutList.listIterator();
        List<WorkoutVO> workoutVOList = new ArrayList<>();
        while (workoutListIterator.hasNext()) {
            WorkoutVO workoutVO = transformer.transformToVO(workoutListIterator.next());
            workoutVOList.add(workoutVO);
        }

        logger.info("Rest: Success Fetching all Workouts ");

        // Return the list
        return workoutVOList;
    }
}
