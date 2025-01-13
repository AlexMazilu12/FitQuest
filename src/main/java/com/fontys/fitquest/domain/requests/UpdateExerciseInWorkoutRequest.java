package com.fontys.fitquest.domain.requests;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateExerciseInWorkoutRequest {
    private long workoutPlanId;
    private long exerciseId;

    @Min(1)
    private int sets;

    @Min(1)
    private int reps;

    @Min(0)
    private Integer restTime;
}