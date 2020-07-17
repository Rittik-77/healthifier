package com.ricky.healthifier.controller.workout;

public class WorkoutVO {

    private String name;
    private Double caloriesBurntPerHour;

    public WorkoutVO() {

    }

    public WorkoutVO(String name, Double caloriesBurntPerHour) {
        this.name = name;
        this.caloriesBurntPerHour = caloriesBurntPerHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCaloriesBurntPerHour() {
        return caloriesBurntPerHour;
    }

    public void setCaloriesBurntPerHour(Double caloriesBurntPerHour) {
        this.caloriesBurntPerHour = caloriesBurntPerHour;
    }

    @Override
    public String toString() {
        return "WorkoutVO{" +
                ", name='" + name + '\'' +
                ", caloriesBurntPerHour=" + caloriesBurntPerHour +
                '}';
    }
}
