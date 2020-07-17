package com.ricky.healthifier.entity.workout;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "WorkoutDTO")
@Table(name = "workout")
public class WorkoutDTO {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "calories_per_hour")
    private double caloriesBurntPerHour;

    public WorkoutDTO() {

    }

    public WorkoutDTO(String name, double caloriesBurntPerHour) {
        this.name = name;
        this.caloriesBurntPerHour = caloriesBurntPerHour;
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
        return "WorkoutDTO{" +
                ", name='" + name + '\'' +
                ", caloriesBurntPerHour=" + caloriesBurntPerHour +
                '}';
    }
}
