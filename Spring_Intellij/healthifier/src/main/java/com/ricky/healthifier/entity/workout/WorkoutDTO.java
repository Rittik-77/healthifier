package com.ricky.healthifier.entity.workout;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "WorkoutDTO")
@Table(name = "workout")
public class WorkoutDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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
        return "WorkoutDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", caloriesBurntPerHour=" + caloriesBurntPerHour +
                '}';
    }
}
