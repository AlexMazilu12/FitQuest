package com.fontys.fitquest.domain.requests;

import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddExerciseToWorkoutRequest {
    private long workoutPlanId;

    @NotNull(message = "Select an exercise")
    private ExerciseEntity exercise;

    @Min(value = 1, message = "Sets must be at least 1")
    private int sets;

    @Min(value = 1, message = "Reps must be at least 1")
    private int reps;
    private Integer restTime;
}