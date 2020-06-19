package com.ricky.healthifier.datamodel.food;

public class Food {

    private int id;
    private String name;
    private QuantityEnum qtyEnum;
    private double qty;
    private double calories;

    public Food() {

    }

    public Food(String name, QuantityEnum qtyEnum, double qty, double calories) {
        this.name = name;
        this.qtyEnum = qtyEnum;
        this.qty = qty;
        this.calories = calories;
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

    public QuantityEnum getQtyEnum() {
        return qtyEnum;
    }

    public void setQtyEnum(QuantityEnum qtyEnum) {
        this.qtyEnum = qtyEnum;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qtyEnum=" + qtyEnum +
                ", qty=" + qty +
                ", calories=" + calories +
                '}';
    }
}
