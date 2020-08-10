package com.ricky.healthifier.service.tracker;

import com.ricky.healthifier.datamodel.tracker.WorkoutTracker;

import java.util.Comparator;

public class WorkoutTrackerSorter implements Comparator<WorkoutTracker> {

    @Override
    public int compare(WorkoutTracker o1, WorkoutTracker o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
