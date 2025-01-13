package com.fontys.fitquest.domain.requests;

import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddExerciseToWorkoutRequest {
    private long workoutPlanId;

    @NotNull
    private ExerciseEntity exercise;

    @Min(1)
    private int sets;

    @Min(1)
    private int reps;
    private Integer restTime;
}