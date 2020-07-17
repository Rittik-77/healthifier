package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.datamodel.tracker.FoodTracker;
import com.ricky.healthifier.utils.exception.AppException;

import java.util.List;

public interface FoodTrackerService {

    boolean addFoodToTracker(FoodTracker foodTracker) throws AppException;
}
