package com.ricky.healthifier.entity.food;

import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.datamodel.food.QuantityEnum;
import com.ricky.healthifier.utils.exception.AppException;
import com.ricky.healthifier.utils.exception.ExceptionLevel;

public class FoodDTOTransformer {

    private static final String ERROR_FETCHING_DATA = "Error Fetching Data";
    private static final String INVALID_QTY_ENUM = "There is some invalid entry in the food database. " +
                                                    "The qty_enum field is not an acceptable value";

    public Food transformToModel(FoodDTO foodDTO) throws AppException {
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setQtyEnum(stringToEnumConverter(foodDTO.getQtyEnumDTO().getQtyEnum()));
        food.setQty(foodDTO.getQty());
        food.setCalories(foodDTO.getCalories());
        food.setImageUrl(foodDTO.getImageUrl());
        return food;
    }

    private QuantityEnum stringToEnumConverter(String qtyEnum) throws AppException {
        switch (qtyEnum) {
            case "GRAM": return QuantityEnum.GRAM;
            case "ML": return QuantityEnum.ML;
            case "NUMBER": return QuantityEnum.NUMBER;
        }

        throw new AppException(ERROR_FETCHING_DATA, ExceptionLevel.ERROR, INVALID_QTY_ENUM);
    }
}
