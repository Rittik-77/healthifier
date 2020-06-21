package com.ricky.healthifier.entity.food;

import javax.persistence.*;

@Entity(name = "FoodDTO")
@Table(name = "food")
public class FoodDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qty_enum")
    private QuantityEnumDTO qtyEnumDTO;

    @Column(name = "qty")
    private double qty;

    @Column(name = "calories")
    private double calories;

    @Column(name = "image_url")
    private String imageUrl;

    public FoodDTO() {

    }

    public FoodDTO(String name, QuantityEnumDTO qtyEnumDTO, double qty, double calories, String imageUrl) {
        this.name = name;
        this.qtyEnumDTO = qtyEnumDTO;
        this.qty = qty;
        this.calories = calories;
        this.imageUrl = imageUrl;
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

    public QuantityEnumDTO getQtyEnumDTO() {
        return qtyEnumDTO;
    }

    public void setQtyEnumDTO(QuantityEnumDTO qtyEnumDTO) {
        this.qtyEnumDTO = qtyEnumDTO;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "FoodDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qtyEnumDTO=" + qtyEnumDTO +
                ", qty=" + qty +
                ", calories=" + calories +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
