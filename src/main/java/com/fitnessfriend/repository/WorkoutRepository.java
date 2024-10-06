package com.fitnessfriend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitnessfriend.entity.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
	// Add custom queries here if needed

}
