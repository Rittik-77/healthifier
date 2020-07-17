package com.ricky.healthifier.controller.tracker;

import com.ricky.healthifier.datamodel.tracker.FoodTracker;
import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;

public class WorkoutTrackerVOTransformer {

    public WorkoutTracker transformToModel(WorkoutTrackerVO workoutTrackerVO) {
        WorkoutTracker workoutTracker = new WorkoutTracker();
        if(workoutTrackerVO.getId() != null)
            workoutTracker.setId(workoutTrackerVO.getId());
        if(workoutTrackerVO.getDuration() != null)
            workoutTracker.setDuration(workoutTrackerVO.getDuration());
        if(workoutTrackerVO.getDate() != null)
            workoutTracker.setDate(workoutTrackerVO.getDate());
        if(workoutTrackerVO.getEmail() != null)
            workoutTracker.setEmail(workoutTrackerVO.getEmail());
        if(workoutTrackerVO.getWorkoutName() != null)
            workoutTracker.setWorkoutName(workoutTrackerVO.getWorkoutName());
        return workoutTracker;
    }
}
