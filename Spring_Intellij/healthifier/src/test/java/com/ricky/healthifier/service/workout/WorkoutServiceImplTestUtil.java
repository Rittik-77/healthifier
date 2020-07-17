package com.ricky.healthifier.service.workout;

import com.ricky.healthifier.datamodel.workout.Workout;
import org.junit.Assert;

public class WorkoutServiceImplTestUtil {

    public static void assertNotNull(Workout workout) {
        Assert.assertNotNull(workout.getName());
        Assert.assertNotNull(workout.getCaloriesBurntPerHour());
    }
}
