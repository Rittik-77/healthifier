package com.ricky.healthifier.entity.tracker;

import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;
import com.ricky.healthifier.datamodel.workout.Workout;
import com.ricky.healthifier.entity.workout.WorkoutDTO;

public class WorkoutTrackerDTOTransformer {

    public WorkoutTrackerDTO transformToDTO(WorkoutTracker workoutTracker) {
        WorkoutTrackerDTO workoutTrackerDTO = new WorkoutTrackerDTO();
        if(workoutTracker.getId() != null)
        workoutTrackerDTO.setId(workoutTracker.getId());
        if(workoutTracker.getDate() != null)
        workoutTrackerDTO.setDate(workoutTracker.getDate());
        if(workoutTracker.getDuration() != null)
        workoutTrackerDTO.setDuration(workoutTracker.getDuration());
        return workoutTrackerDTO;
    }
}
