package com.ricky.healthifier.dao;

import com.ricky.healthifier.entity.workout.WorkoutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutDAO extends JpaRepository<WorkoutDTO, Integer> {

    Optional<WorkoutDTO> findWorkoutDTOByName(String name);
}
