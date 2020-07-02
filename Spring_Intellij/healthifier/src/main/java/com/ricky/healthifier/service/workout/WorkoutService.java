package com.ricky.healthifier.service.workout;

import com.ricky.healthifier.datamodel.workout.Workout;
import com.ricky.healthifier.utils.exception.AppException;

import java.util.List;

public interface WorkoutService {

    List<Workout> getAllWorkouts() throws AppException;
}
