package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.datamodel.tracker.FoodTracker;

import java.util.Comparator;

public class FoodTrackerSorter implements Comparator<FoodTracker> {

    @Override
    public int compare(FoodTracker o1, FoodTracker o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
