package com.fontys.fitquest.domain.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetExercisesForWorkoutRequest {
    private long workoutPlanId;
}