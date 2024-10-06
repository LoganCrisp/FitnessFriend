package com.fitnessfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessfriend.dto.WorkoutDto;
import com.fitnessfriend.entity.Workout;
import com.fitnessfriend.exception.ResourceNotFoundException;
import com.fitnessfriend.mapper.WorkoutMapper;
import com.fitnessfriend.repository.WorkoutRepository;

@Service
public class WorkoutService {

	@Autowired
	private WorkoutRepository workoutRepository;

	// Return a list of DTOs, mapped from entities
	public List<WorkoutDto> getAllWorkouts() {
		List<Workout> workouts = workoutRepository.findAll();
		return WorkoutMapper.toDtoList(workouts);
	}

	// Return a single DTO, mapped from entity
	public WorkoutDto getWorkoutById(Long id) {
		Workout workout = workoutRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
		return WorkoutMapper.toDto(workout);
	}

	// Create a new workout entity from DTO and save it
	public WorkoutDto createWorkout(WorkoutDto workoutDto) {
		Workout workout = WorkoutMapper.toEntity(workoutDto);
		Workout savedWorkout = workoutRepository.save(workout);
		return WorkoutMapper.toDto(savedWorkout);
	}

	// Update an existing workout entity from DTO
	public WorkoutDto updateWorkout(Long id, WorkoutDto workoutDto) {
		Workout workout = workoutRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
		workout.setName(workoutDto.getName());
		workout.setDescription(workoutDto.getDescription());
		Workout updatedWorkout = workoutRepository.save(workout);
		return WorkoutMapper.toDto(updatedWorkout);
	}

	public void deleteWorkout(Long id) {
		Workout workout = workoutRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
		workoutRepository.delete(workout);
	}
}
