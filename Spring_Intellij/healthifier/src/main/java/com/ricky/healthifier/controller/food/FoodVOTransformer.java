package com.ricky.healthifier.controller.food;

import com.ricky.healthifier.datamodel.food.Food;

public class FoodVOTransformer {

    public FoodVO transformToVO(Food food) {
        FoodVO foodVO = new FoodVO();
        foodVO.setId(food.getId());
        foodVO.setName(food.getName());
        foodVO.setQtyEnum(food.getQtyEnum());
        foodVO.setQty(food.getQty());
        foodVO.setCalories(food.getCalories());
        return foodVO;
    }
}
