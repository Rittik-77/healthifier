package com.ricky.healthifier.controller.workout;

public class WorkoutVO {

    private int id;
    private String name;
    private double caloriesBurntPerHour;

    public WorkoutVO() {

    }

    public WorkoutVO(String name, double caloriesBurntPerHour) {
        this.name = name;
        this.caloriesBurntPerHour = caloriesBurntPerHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloriesBurntPerHour() {
        return caloriesBurntPerHour;
    }

    public void setCaloriesBurntPerHour(double caloriesBurntPerHour) {
        this.caloriesBurntPerHour = caloriesBurntPerHour;
    }

    @Override
    public String toString() {
        return "WorkoutVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", caloriesBurntPerHour=" + caloriesBurntPerHour +
                '}';
    }
}
