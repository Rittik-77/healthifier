package com.ricky.healthifier.controller.workout;

import com.ricky.healthifier.datamodel.workout.Workout;

public class WorkoutVOTransformer {

    public WorkoutVO transformToVO(Workout workout) {
        WorkoutVO workoutVO = new WorkoutVO();
        workoutVO.setId(workout.getId());
        workoutVO.setName(workout.getName());
        workoutVO.setCaloriesBurntPerHour(workout.getCaloriesBurntPerHour());
        return workoutVO;
    }
}
