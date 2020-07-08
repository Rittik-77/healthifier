package com.ricky.healthifier.entity.user;

import javax.persistence.*;

@Entity(name = "UserDTO")
@Table(name = "user")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "weight")
    private double weight;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "role_enum")
    private RoleEnumDTO roleEnumDTO;

    public UserDTO() {

    }

    public UserDTO(String email, String username, String password, double weight, RoleEnumDTO roleEnumDTO) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.roleEnumDTO = roleEnumDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public RoleEnumDTO getRoleEnumDTO() {
        return roleEnumDTO;
    }

    public void setRoleEnumDTO(RoleEnumDTO roleEnumDTO) {
        this.roleEnumDTO = roleEnumDTO;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", weight=" + weight +
                ", roleEnumDTO=" + roleEnumDTO +
                '}';
    }
}
