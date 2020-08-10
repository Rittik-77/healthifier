package com.ricky.healthifier.controller.summary;

import java.time.LocalDate;

public class SummaryVO {

    private LocalDate date;
    private double calories;

    public SummaryVO() {

    }

    public SummaryVO(LocalDate date, double calories) {
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
