package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.*;
import com.fontys.fitquest.domain.requests.*;
import com.fontys.fitquest.domain.responses.*;
import com.fontys.fitquest.domain.Exercise;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@AllArgsConstructor
public class ExerciseController {
    private final GetAllExercisesUseCase getAllExercisesUseCase;
    private final GetExerciseUseCase getExerciseUseCase;
    private final CreateExerciseUseCase createExerciseUseCase;
    private final UpdateExerciseUseCase updateExerciseUseCase;
    private final DeleteExerciseUseCase deleteExerciseUseCase;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = getAllExercisesUseCase.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable int id) {
        Exercise exercise = getExerciseUseCase.getExercise(id);
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    public ResponseEntity<CreateExerciseResponse> createExercise(@RequestBody CreateExerciseRequest request) {
        CreateExerciseResponse response = createExerciseUseCase.createExercise(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateExerciseResponse> updateExercise(@PathVariable int id, @RequestBody UpdateExerciseRequest request) {
        request.setId(id);
        UpdateExerciseResponse response = updateExerciseUseCase.updateExercise(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable int id) {
        deleteExerciseUseCase.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}