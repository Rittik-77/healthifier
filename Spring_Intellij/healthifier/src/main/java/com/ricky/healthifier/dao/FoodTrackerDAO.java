package com.ricky.healthifier.dao;

import com.ricky.healthifier.entity.tracker.FoodTrackerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTrackerDAO extends JpaRepository<FoodTrackerDTO, Integer> {

}
