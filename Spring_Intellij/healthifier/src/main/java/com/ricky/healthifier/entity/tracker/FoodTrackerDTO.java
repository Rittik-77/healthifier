package com.ricky.healthifier.entity.tracker;

import com.ricky.healthifier.entity.food.FoodDTO;
import com.ricky.healthifier.entity.user.UserDTO;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "FoodTrackerDTO")
@Table(name = "food_tracker")
public class FoodTrackerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "food_name")
    private FoodDTO foodDTO;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "email")
    private UserDTO userDTO;

    public FoodTrackerDTO() {

    }

    public FoodTrackerDTO(Date date, FoodDTO foodDTO, double amount, UserDTO userDTO) {
        this.date = date;
        this.foodDTO = foodDTO;
        this.amount = amount;
        this.userDTO = userDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FoodDTO getFoodDTO() {
        return foodDTO;
    }

    public void setFoodDTO(FoodDTO foodDTO) {
        this.foodDTO = foodDTO;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "FoodTrackerDTO{" +
                "id=" + id +
                ", date=" + date +
                ", foodDTO=" + foodDTO +
                ", amount=" + amount +
                ", userDTO=" + userDTO +
                '}';
    }
}
