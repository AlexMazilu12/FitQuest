package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.DeleteWorkoutPlanRequest;
import com.fontys.fitquest.domain.responses.DeleteWorkoutPlanResponse;

public interface DeleteWorkoutPlanUseCase {
    DeleteWorkoutPlanResponse deleteWorkoutPlan(DeleteWorkoutPlanRequest request);
}