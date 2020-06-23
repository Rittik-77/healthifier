package com.ricky.healthifier.init;

import com.ricky.healthifier.service.food.FoodService;
import com.ricky.healthifier.utils.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DataBaseInitializr {

    @Autowired
    private FoodService foodService;

    @PostConstruct
    public void feedValuesToDB() throws AppException {
        boolean response = foodService.initializeFoodDB();

        if(!response) {
            throw new AppException("Error initializing Food DB");
        }
    }

    @PreDestroy
    public void deleteValuesFromDB() throws AppException {
        boolean response = foodService.deleteFoodDBAtExit();
        if(!response) {
            throw new AppException("Error deleting Food DB");
        }
    }
}
