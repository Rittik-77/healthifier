package com.ricky.healthifier.entity.workout;

import com.ricky.healthifier.datamodel.workout.Workout;

public class WorkoutDTOTransformer {

    public Workout transformToModel(WorkoutDTO workoutDTO) {
        Workout workout = new Workout();
        workout.setId(workoutDTO.getId());
        workout.setName(workoutDTO.getName());
        workout.setWeightPersonInKg(workoutDTO.getWeightPersonInKg());
        workout.setCaloriesBurntPerHour(workoutDTO.getCaloriesBurntPerHour());
        return workout;
    }
}
