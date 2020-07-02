package com.ricky.healthifier.service.workout;

import com.ricky.healthifier.dao.WorkoutDAO;
import com.ricky.healthifier.datamodel.workout.Workout;
import com.ricky.healthifier.entity.workout.WorkoutDTO;
import com.ricky.healthifier.entity.workout.WorkoutDTOTransformer;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutDAO workoutDAO;

    private final Logger logger = LoggerFactory.getLogger(WorkoutServiceImpl.class);
    private final WorkoutDTOTransformer transformer = new WorkoutDTOTransformer();

    @Override
    public List<Workout> getAllWorkouts() throws AppException {

        logger.info("Service: Fetch All Workouts");

        // Retrieve the list from Database
        List<WorkoutDTO> workoutDTOList = workoutDAO.findAll();

        // If list is empty, Throw an exception with UI Message
        if(workoutDTOList.isEmpty()) {
            throw new AppException("Workout List is Empty", ExceptionLevel.INFO, "Workout DataBase is Empty");
        }

        // Transform the list
        ListIterator<WorkoutDTO> workoutDTOListIterator = workoutDTOList.listIterator();
        List<Workout> workoutList = new ArrayList<>();
        while (workoutDTOListIterator.hasNext()) {
            Workout workout = transformer.transformToModel(workoutDTOListIterator.next());
            workoutList.add(workout);
        }

        logger.info("Service: Success fetching all Workouts");

        // Return list of Workouts
        return workoutList;
    }
}
