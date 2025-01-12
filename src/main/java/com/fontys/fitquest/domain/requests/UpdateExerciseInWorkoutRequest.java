package com.fontys.fitquest.domain.requests;

import lombok.Data;

@Data
public class UpdateExerciseInWorkoutRequest {
    private long workoutPlanId;
    private long exerciseId;
    private int sets;
    private int reps;
    private Integer restTime;
}