package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.UpdateWorkoutPlanRequest;
import com.fontys.fitquest.domain.UpdateWorkoutPlanResponse;

public interface UpdateWorkoutPlanUseCase {
    UpdateWorkoutPlanResponse updateWorkoutPlan(UpdateWorkoutPlanRequest request);
}