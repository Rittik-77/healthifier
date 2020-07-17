package com.ricky.healthifier.entity.workout;

import com.ricky.healthifier.datamodel.workout.Workout;

public class WorkoutDTOTransformer {

    public Workout transformToModel(WorkoutDTO workoutDTO) {
        Workout workout = new Workout();
        workout.setName(workoutDTO.getName());
        workout.setCaloriesBurntPerHour(workoutDTO.getCaloriesBurntPerHour());
        return workout;
    }
}
