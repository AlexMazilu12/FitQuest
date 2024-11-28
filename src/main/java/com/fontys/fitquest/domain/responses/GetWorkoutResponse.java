package com.fontys.fitquest.domain.responses;

import com.fontys.fitquest.domain.WorkoutPlan;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetWorkoutResponse {
    private WorkoutPlan workoutPlan;
}
