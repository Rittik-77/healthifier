package com.ricky.healthifier.entity.tracker;

import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;

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

    public WorkoutTracker transformToModel(WorkoutTrackerDTO workoutTrackerDTO) {
        WorkoutTracker workoutTracker = new WorkoutTracker();
        workoutTracker.setId(workoutTrackerDTO.getId());
        workoutTracker.setDate(workoutTrackerDTO.getDate());
        workoutTracker.setDuration(workoutTrackerDTO.getDuration());
        workoutTracker.setEmail(workoutTrackerDTO.getUserDTO().getEmail());
        workoutTracker.setWorkoutName(workoutTrackerDTO.getWorkoutDTO().getName());
        return workoutTracker;
    }
}
