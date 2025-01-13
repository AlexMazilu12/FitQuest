package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.*;
import com.fontys.fitquest.domain.WorkoutPlan;
import com.fontys.fitquest.domain.requests.*;
import com.fontys.fitquest.domain.responses.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workouts")
@AllArgsConstructor
@Validated
public class WorkoutController {
    private final GetWorkoutUseCase getWorkoutUseCase;
    private final GetAllWorkoutsUseCase getAllWorkoutsUseCase;
    private final DeleteWorkoutPlanUseCase deleteWorkoutPlanUseCase;
    private final CreateWorkoutPlanUseCase createWorkoutPlanUseCase;
    private final UpdateWorkoutPlanUseCase updateWorkoutPlanUseCase;
    private final AddExerciseToWorkoutUseCase addExerciseToWorkoutUseCase;
    private final GetExercisesForWorkoutUseCase getExercisesForWorkoutUseCase;
    private final UpdateExerciseInWorkoutUseCase updateExerciseInWorkoutUseCase;
    private final DeleteExerciseFromWorkoutUseCase deleteExerciseFromWorkoutUseCase;

    @GetMapping("{id}")
    public ResponseEntity<WorkoutPlan> getWorkout(@PathVariable(value = "id") final long id) {
        GetWorkoutRequest request = new GetWorkoutRequest(id);
        GetWorkoutResponse response = getWorkoutUseCase.getWorkout(request);
        if (response.getWorkoutPlan() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response.getWorkoutPlan());
    }

    @GetMapping
    public ResponseEntity<GetAllWorkoutsResponse> getAllWorkouts() {
        GetAllWorkoutsRequest request = new GetAllWorkoutsRequest();
        GetAllWorkoutsResponse response = getAllWorkoutsUseCase.getAllWorkouts(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable long id) {
        DeleteWorkoutPlanRequest request = new DeleteWorkoutPlanRequest(id, 0); // Assuming userId is not needed
        deleteWorkoutPlanUseCase.deleteWorkoutPlan(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CreateWorkoutPlanResponse> createWorkout(@RequestBody @Valid CreateWorkoutPlanRequest request) {
        CreateWorkoutPlanResponse response = createWorkoutPlanUseCase.createWorkoutPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateWorkout(@PathVariable("id") long id,
                                              @RequestBody @Valid UpdateWorkoutPlanRequest request) {
        request.setId(id);
        updateWorkoutPlanUseCase.updateWorkoutPlan(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/exercises")
    public ResponseEntity<AddExerciseToWorkoutResponse> addExerciseToWorkout(@PathVariable("id") long workoutPlanId,
                                                                             @RequestBody @Valid AddExerciseToWorkoutRequest request) {
        request.setWorkoutPlanId(workoutPlanId);
        if (exerciseAlreadyExists(workoutPlanId, request.getExercise().getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        AddExerciseToWorkoutResponse response = addExerciseToWorkoutUseCase.addExerciseToWorkout(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}/exercises")
    public ResponseEntity<GetExercisesForWorkoutResponse> getExercisesForWorkout(@PathVariable("id") Long workoutPlanId) {
        GetExercisesForWorkoutRequest request = new GetExercisesForWorkoutRequest(workoutPlanId);
        GetExercisesForWorkoutResponse response = getExercisesForWorkoutUseCase.getExercisesForWorkout(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{workoutPlanId}/exercises/{exerciseId}")
    @RolesAllowed({"USER", "TRAINER"})
    public ResponseEntity<UpdateExerciseInWorkoutResponse> updateExerciseInWorkout(@PathVariable("workoutPlanId") long workoutPlanId,
                                                                                   @PathVariable("exerciseId") long exerciseId,
                                                                                   @RequestBody @Valid UpdateExerciseInWorkoutRequest request) {
        request.setWorkoutPlanId(workoutPlanId);
        request.setExerciseId(exerciseId);
        UpdateExerciseInWorkoutResponse response = updateExerciseInWorkoutUseCase.updateExerciseInWorkout(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{workoutPlanId}/exercises/{exerciseId}")
    public ResponseEntity<DeleteExerciseFromWorkoutResponse> deleteExerciseFromWorkout(@PathVariable long workoutPlanId, @PathVariable long exerciseId) {
        DeleteExerciseFromWorkoutRequest request = new DeleteExerciseFromWorkoutRequest();
        request.setWorkoutPlanId(workoutPlanId);
        request.setExerciseId(exerciseId);
        DeleteExerciseFromWorkoutResponse response = deleteExerciseFromWorkoutUseCase.deleteExerciseFromWorkout(request);
        return ResponseEntity.ok(response);
    }

    private boolean exerciseAlreadyExists(long workoutPlanId, int exerciseId) {
        GetExercisesForWorkoutRequest request = new GetExercisesForWorkoutRequest(workoutPlanId);
        GetExercisesForWorkoutResponse response = getExercisesForWorkoutUseCase.getExercisesForWorkout(request);
        return response.getExercises().stream()
                .anyMatch(exercise -> exercise.getId() == exerciseId);
    }
}