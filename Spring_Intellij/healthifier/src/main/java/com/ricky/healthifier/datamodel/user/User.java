package com.ricky.healthifier.datamodel.user;

public class User {

    private String email;
    private String username;
    private String password;
    private Double weight;
    private RoleEnum roleEnum;

    public User() {

    }

    public User(String email, String username, String password, Double weight, RoleEnum roleEnum) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.roleEnum = roleEnum;
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

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", weight=" + weight +
                ", roleEnum=" + roleEnum +
                '}';
    }
}
