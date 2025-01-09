package com.fontys.fitquest.domain.requests;

import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import lombok.Data;

@Data
public class AddExerciseToWorkoutRequest {
    private long workoutPlanId;
    private ExerciseEntity exercise;
    private int sets;
    private int reps;
    private Integer restTime;
}