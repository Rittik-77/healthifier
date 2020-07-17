package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;
import com.ricky.healthifier.utils.exception.AppException;

public interface WorkoutTrackerService {

    boolean addWorkoutToTracker(WorkoutTracker workoutTracker) throws AppException;
}
