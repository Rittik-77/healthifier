package com.ricky.healthifier.entity.food;

import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.datamodel.food.QuantityEnum;

public class FoodDTOTransformer {

    public Food transformToModel(FoodDTO foodDTO) {
        Food food = new Food();
        food.setId(foodDTO.getId());
        food.setName(foodDTO.getName());
        food.setQtyEnum(stringToEnumConverter(foodDTO.getQtyEnumDTO().getQtyEnum()));
        food.setQty(foodDTO.getQty());
        food.setCalories(foodDTO.getCalories());
        return food;
    }

    private QuantityEnum stringToEnumConverter(String qtyEnum) {
        switch (qtyEnum) {
            case "GRAM": return QuantityEnum.GRAM;
            case "ML": return QuantityEnum.ML;
            case "NUMBER": return QuantityEnum.NUMBER;
        }

        //TODO: Throw an exception
        return null;
    }
}
