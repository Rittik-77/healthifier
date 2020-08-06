package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;
import com.ricky.healthifier.utils.exception.AppException;

import java.util.List;

public interface WorkoutTrackerService {

    boolean addWorkoutToTracker(WorkoutTracker workoutTracker, String token) throws AppException;

    List<WorkoutTracker> getWorkoutTracker(String token) throws AppException;

    boolean updateWorkoutInTracker(Integer id, WorkoutTracker workoutTracker, String token) throws AppException;

    boolean deleteWorkoutFromTracker(Integer id, String token) throws AppException;
}
