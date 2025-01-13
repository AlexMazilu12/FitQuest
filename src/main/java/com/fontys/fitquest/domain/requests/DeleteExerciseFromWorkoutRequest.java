package com.fontys.fitquest.domain.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteExerciseFromWorkoutRequest {
    @NotNull
    private Long workoutPlanId;

    @NotNull
    private Long exerciseId;
}