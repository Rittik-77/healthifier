package com.ricky.healthifier.controller.workout;

import com.ricky.healthifier.datamodel.workout.Workout;

public class WorkoutVOTransformer {

    public WorkoutVO transformToVO(Workout workout) {
        WorkoutVO workoutVO = new WorkoutVO();
        workoutVO.setName(workout.getName());
        workoutVO.setCaloriesBurntPerHour(workout.getCaloriesBurntPerHour());
        return workoutVO;
    }
}
