package com.ricky.healthifier.controller.tracker;

import com.ricky.healthifier.datamodel.tracker.FoodTracker;

public class FoodTrackerVOTransformer {

    public FoodTracker transformToModel(FoodTrackerVO foodTrackerVO) {
        FoodTracker foodTracker = new FoodTracker();
        if(foodTrackerVO.getId() != null)
            foodTracker.setId(foodTrackerVO.getId());
        if(foodTrackerVO.getAmount() != null)
            foodTracker.setAmount(foodTrackerVO.getAmount());
        if(foodTrackerVO.getDate() != null)
            foodTracker.setDate(foodTrackerVO.getDate());
        if(foodTrackerVO.getEmail() != null)
            foodTracker.setEmail(foodTrackerVO.getEmail());
        if(foodTrackerVO.getFoodName() != null)
            foodTracker.setFoodName(foodTrackerVO.getFoodName());
        return foodTracker;
    }
}
