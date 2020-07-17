package com.ricky.healthifier.dao;

import com.ricky.healthifier.entity.tracker.WorkoutTrackerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutTrackerDAO extends JpaRepository<WorkoutTrackerDTO, Integer> {

}
