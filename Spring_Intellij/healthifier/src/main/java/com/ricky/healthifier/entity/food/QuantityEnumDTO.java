package com.ricky.healthifier.entity.food;

import javax.persistence.*;

@Entity(name = "QuantityEnumDTO")
@Table(name = "quantity_enum")
public class QuantityEnumDTO {

    @Id
    @Column(name = "qty_enum")
    private String qtyEnum;

    public QuantityEnumDTO() {

    }

    public QuantityEnumDTO(String qtyEnum) {
        this.qtyEnum = qtyEnum;
    }

    public String getQtyEnum() {
        return qtyEnum;
    }

    public void setQtyEnum(String qtyEnum) {
        this.qtyEnum = qtyEnum;
    }

    @Override
    public String toString() {
        return "QuantityEnumDTO{" +
                "qtyEnum='" + qtyEnum + '\'' +
                '}';
    }
}
