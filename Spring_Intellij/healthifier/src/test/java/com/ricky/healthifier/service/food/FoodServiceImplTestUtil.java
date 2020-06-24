package com.ricky.healthifier.service.food;

import com.ricky.healthifier.datamodel.food.Food;
import org.junit.Assert;

public class FoodServiceImplTestUtil {

    public static void assertNotNull(Food food) {
        Assert.assertNotNull(food.getId());
        Assert.assertNotNull(food.getName());
        Assert.assertNotNull(food.getQtyEnum());
        Assert.assertNotNull(food.getQty());
        Assert.assertNotNull(food.getCalories());
        Assert.assertNotNull(food.getImageUrl());
    }
}
