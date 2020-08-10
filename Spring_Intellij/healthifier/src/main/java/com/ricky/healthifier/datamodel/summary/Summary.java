package com.ricky.healthifier.datamodel.summary;

import java.time.LocalDate;

public class Summary {

    private LocalDate date;
    private double calories;

    public Summary() {

    }

    public Summary(LocalDate date, double calories) {
        this.date = date;
        this.calories = calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
