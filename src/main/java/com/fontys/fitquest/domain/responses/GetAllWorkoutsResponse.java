package com.fontys.fitquest.domain.responses;

import com.fontys.fitquest.domain.WorkoutPlan;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllWorkoutsResponse {
    private List<WorkoutPlan> workoutPlans;
}