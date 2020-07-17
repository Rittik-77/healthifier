package com.ricky.healthifier.controller.food;

import com.ricky.healthifier.datamodel.food.QuantityEnum;

public class FoodVO {

    private String name;
    private QuantityEnum qtyEnum;
    private Double qty;
    private Double calories;
    private String imageUrl;

    public FoodVO() {

    }

    public FoodVO(String name, QuantityEnum qtyEnum, Double qty, Double calories, String imageUrl) {
        this.name = name;
        this.qtyEnum = qtyEnum;
        this.qty = qty;
        this.calories = calories;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuantityEnum getQtyEnum() {
        return qtyEnum;
    }

    public void setQtyEnum(QuantityEnum qtyEnum) {
        this.qtyEnum = qtyEnum;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Food{" +
                ", name='" + name + '\'' +
                ", qtyEnum=" + qtyEnum +
                ", qty=" + qty +
                ", calories=" + calories +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
