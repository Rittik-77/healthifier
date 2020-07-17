package com.ricky.healthifier.datamodel.workout;

public class Workout {

    private String name;
    private Double caloriesBurntPerHour;

    public Workout() {

    }

    public Workout(String name, Double caloriesBurntPerHour) {
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
        return "Workout{" +
                ", name='" + name + '\'' +
                ", caloriesBurntPerHour=" + caloriesBurntPerHour +
                '}';
    }
}
