package com.fontys.fitquest.domain.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignWorkoutRequest {
    @NotNull
    private Long workoutId;

    @NotNull
    private Integer userId;
}