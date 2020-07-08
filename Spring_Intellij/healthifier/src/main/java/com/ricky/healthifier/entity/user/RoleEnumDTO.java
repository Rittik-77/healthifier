package com.ricky.healthifier.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "RoleEnumDTO")
@Table(name = "role_enum")
public class RoleEnumDTO {

    @Id
    @Column(name = "role")
    private String role;

    public RoleEnumDTO() {

    }

    public RoleEnumDTO(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleEnumDTO{" +
                "role='" + role + '\'' +
                '}';
    }
}
