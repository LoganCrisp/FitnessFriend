package com.fitnessfriend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.fitnessfriend.dto.WorkoutDto;
import com.fitnessfriend.entity.Workout;

public class WorkoutMapper {

	// Convert entity to DTO
	public static WorkoutDto toDto(Workout workout) {
		WorkoutDto dto = new WorkoutDto();
		dto.setId(workout.getId());
		dto.setName(workout.getName());
		dto.setDescription(workout.getDescription());
		return dto;
	}

	// Convert DTO to entity
	public static Workout toEntity(WorkoutDto dto) {
		Workout workout = new Workout();
		workout.setName(dto.getName());
		workout.setDescription(dto.getDescription());
		return workout;
	}

	// Convert list of entities to list of DTOs
	public static List<WorkoutDto> toDtoList(List<Workout> workouts) {
		return workouts.stream().map(WorkoutMapper::toDto).collect(Collectors.toList());
	}

	// Convert list of DTOs to list of entities
	public static List<Workout> toEntityList(List<WorkoutDto> workoutDtos) {
		return workoutDtos.stream().map(WorkoutMapper::toEntity).collect(Collectors.toList());
	}
}
