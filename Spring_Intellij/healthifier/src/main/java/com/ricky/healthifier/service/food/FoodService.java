package com.ricky.healthifier.service.food;

import com.ricky.healthifier.datamodel.food.Food;
import com.ricky.healthifier.utils.exception.AppException;

import java.util.List;

public interface FoodService {

    List<Food> getAllFoods() throws AppException;

    Food getFoodByName(String foodName) throws AppException;
}
