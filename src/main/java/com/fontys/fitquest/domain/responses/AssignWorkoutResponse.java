package com.fontys.fitquest.domain.responses;

import lombok.Data;

@Data
public class AssignWorkoutResponse {
    private Long workoutId;
    private Integer userId;
    private String message;
}