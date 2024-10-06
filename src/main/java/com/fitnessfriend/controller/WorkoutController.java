package com.fitnessfriend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessfriend.dto.WorkoutDto;
import com.fitnessfriend.exception.ResourceNotFoundException;
import com.fitnessfriend.service.WorkoutService;

/**
 * Controller for managing workouts.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

	/** Connection to WorkoutService */
	@Autowired
	private WorkoutService workoutService;

	/**
	 * REST API method to provide GET access to all workouts in the system
	 *
	 * @return JSON representation of all workouts
	 */
	@GetMapping
	public List<WorkoutDto> getWorkouts() {
		return workoutService.getAllWorkouts();
	}

	/**
	 * REST API method to provide GET access to a specific workout by ID
	 *
	 * @param id workout ID
	 * @return response to the request
	 */
	@GetMapping("{id}")
	public ResponseEntity<WorkoutDto> getWorkout(@PathVariable("id") final Long id) {
		final WorkoutDto workoutDto = workoutService.getWorkoutById(id);
		return ResponseEntity.ok(workoutDto);
	}

	/**
	 * REST API method to provide POST access to create a new workout.
	 *
	 * @param workoutDto The valid Workout to be saved.
	 * @return ResponseEntity indicating success or error.
	 */
	@PostMapping
	public ResponseEntity<WorkoutDto> createWorkout(@RequestBody final WorkoutDto workoutDto) {
		final WorkoutDto savedWorkoutDto = workoutService.createWorkout(workoutDto);
		return ResponseEntity.ok(savedWorkoutDto);
	}

	/**
	 * REST API method to allow deleting a Workout from the system by its ID
	 *
	 * @param id The ID of the workout to delete
	 * @return Success or error response
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteWorkout(@PathVariable("id") final Long id) {
		workoutService.deleteWorkout(id);
		return ResponseEntity.ok("Workout deleted successfully.");
	}

	/**
	 * REST API method to provide PUT access to update a workout.
	 *
	 * @param id         The ID of the workout to update.
	 * @param workoutDto The updated workout data.
	 * @return ResponseEntity indicating success or error.
	 */
	@PutMapping("{id}")
	public ResponseEntity<WorkoutDto> updateWorkout(@PathVariable("id") final Long id,
			@RequestBody final WorkoutDto workoutDto) {
		try {
			final WorkoutDto updatedWorkout = workoutService.updateWorkout(id, workoutDto);
			return ResponseEntity.ok(updatedWorkout);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
