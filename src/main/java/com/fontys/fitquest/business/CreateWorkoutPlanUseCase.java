package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.CreateWorkoutPlanRequest;
import com.fontys.fitquest.domain.responses.CreateWorkoutPlanResponse;

public interface CreateWorkoutPlanUseCase {
    CreateWorkoutPlanResponse createWorkoutPlan(CreateWorkoutPlanRequest request);
}
