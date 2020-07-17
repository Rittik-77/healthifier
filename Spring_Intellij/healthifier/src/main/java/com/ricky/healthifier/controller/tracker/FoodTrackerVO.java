package com.ricky.healthifier.controller.tracker;

import java.util.Date;

public class FoodTrackerVO {

    private Integer id;
    private Date date;
    private String foodName;
    private Double amount;
    private String email;

    public FoodTrackerVO() {

    }

    public FoodTrackerVO(Date date, String foodName, Double amount, String email) {
        this.date = date;
        this.foodName = foodName;
        this.amount = amount;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "FoodTrackerVO{" +
                "id=" + id +
                ", date=" + date +
                ", foodName='" + foodName + '\'' +
                ", amount=" + amount +
                ", email='" + email + '\'' +
                '}';
    }
}
