package com.fontys.fitquest.domain.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetExercisesForWorkoutRequest {
    private Long workoutPlanId;
}