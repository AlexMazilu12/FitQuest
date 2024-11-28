package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.UpdateWorkoutPlanRequest;
import com.fontys.fitquest.domain.responses.UpdateWorkoutPlanResponse;

public interface UpdateWorkoutPlanUseCase {
    UpdateWorkoutPlanResponse updateWorkoutPlan(UpdateWorkoutPlanRequest request);
}