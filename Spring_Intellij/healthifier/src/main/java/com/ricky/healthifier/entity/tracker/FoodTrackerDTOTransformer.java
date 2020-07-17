package com.ricky.healthifier.entity.tracker;

import com.ricky.healthifier.dao.FoodDAO;
import com.ricky.healthifier.dao.UserDAO;
import com.ricky.healthifier.datamodel.tracker.FoodTracker;
import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.user.UserDTO;
import com.ricky.healthifier.utils.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FoodTrackerDTOTransformer {

    public FoodTrackerDTO transformToDTO(FoodTracker foodTracker){

        FoodTrackerDTO foodTrackerDTO = new FoodTrackerDTO();
        if (foodTracker.getId() != null)
            foodTrackerDTO.setId(foodTracker.getId());
        if (foodTracker.getDate() != null)
            foodTrackerDTO.setDate(foodTracker.getDate());
        if (foodTracker.getAmount() != null)
            foodTrackerDTO.setAmount(foodTracker.getAmount());
        return foodTrackerDTO;
    }
}
