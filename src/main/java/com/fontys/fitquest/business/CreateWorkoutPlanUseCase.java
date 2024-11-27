package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.CreateWorkoutPlanRequest;
import com.fontys.fitquest.domain.CreateWorkoutPlanResponse;

public interface CreateWorkoutPlanUseCase {
    CreateWorkoutPlanResponse createWorkoutPlan(CreateWorkoutPlanRequest request);
}
