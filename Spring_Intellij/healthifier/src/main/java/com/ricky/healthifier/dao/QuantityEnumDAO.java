package com.ricky.healthifier.dao;

import com.ricky.healthifier.entity.food.QuantityEnumDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityEnumDAO extends JpaRepository<QuantityEnumDTO, String> {
}
