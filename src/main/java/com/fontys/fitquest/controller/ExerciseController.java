package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.*;
import com.fontys.fitquest.business.exception.DuplicateExerciseException;
import com.fontys.fitquest.business.exception.ExerciseNotFoundException;
import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.domain.requests.CreateExerciseRequest;
import com.fontys.fitquest.domain.requests.UpdateExerciseRequest;
import com.fontys.fitquest.domain.responses.CreateExerciseResponse;
import com.fontys.fitquest.domain.responses.UpdateExerciseResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@AllArgsConstructor
@Validated
public class ExerciseController {
    private final GetAllExercisesUseCase getAllExercisesUseCase;
    private final GetExerciseUseCase getExerciseUseCase;
    private final CreateExerciseUseCase createExerciseUseCase;
    private final UpdateExerciseUseCase updateExerciseUseCase;
    private final DeleteExerciseUseCase deleteExerciseUseCase;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises(
            @RequestParam(required = false) MuscleGroup muscleGroup,
            @RequestParam(required = false, defaultValue = "name") String orderBy,
            @RequestParam(required = false, defaultValue = "asc") String direction) {
        List<Exercise> exercises = getAllExercisesUseCase.getAllExercises(muscleGroup, orderBy, direction);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable int id) {
        Exercise exercise = getExerciseUseCase.getExercise(id);
        return ResponseEntity.ok(exercise);
    }

    @RolesAllowed("ADMIN")
    @PostMapping
    public ResponseEntity<CreateExerciseResponse> createExercise(@Valid @RequestBody CreateExerciseRequest request) {
        try {
            CreateExerciseResponse response = createExerciseUseCase.createExercise(request);
            return ResponseEntity.ok(response);
        } catch (DuplicateExerciseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<UpdateExerciseResponse> updateExercise(@PathVariable int id, @Valid @RequestBody UpdateExerciseRequest request) {
        try {
            request.setId(id);
            UpdateExerciseResponse response = updateExerciseUseCase.updateExercise(request);
            return ResponseEntity.ok(response);
        } catch (ExerciseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable int id) {
        try {
            deleteExerciseUseCase.deleteExercise(id);
            return ResponseEntity.noContent().build();
        } catch (ExerciseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}