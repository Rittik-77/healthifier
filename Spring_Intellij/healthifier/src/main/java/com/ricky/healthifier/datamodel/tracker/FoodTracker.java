package com.ricky.healthifier.datamodel.tracker;

import java.util.Date;

public class FoodTracker {

    private Integer id;
    private Date date;
    private String foodName;
    private Double amount;
    private String email;

    public FoodTracker() {

    }

    public FoodTracker(Date date, String foodName, double amount, String email) {
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
        return "FoodTracker{" +
                "id=" + id +
                ", date=" + date +
                ", foodName='" + foodName + '\'' +
                ", amount=" + amount +
                ", email='" + email + '\'' +
                '}';
    }
}
