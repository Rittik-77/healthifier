package com.ricky.healthifier.dao;

import com.ricky.healthifier.entity.food.FoodDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodDAO extends JpaRepository<FoodDTO, Integer> {

    Optional<FoodDTO> findFoodDTOByName(String name);
}
