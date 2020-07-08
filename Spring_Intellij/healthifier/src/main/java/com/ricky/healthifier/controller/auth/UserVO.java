package com.ricky.healthifier.controller.auth;

public class UserVO {

    private Integer id;
    private String email;
    private String username;
    private String password;
    private Double weight;
    private String role;

    public UserVO() {

    }

    public UserVO(String email, String username, String password, Double weight, String role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", weight=" + weight +
                ", role=" + role +
                '}';
    }
}
