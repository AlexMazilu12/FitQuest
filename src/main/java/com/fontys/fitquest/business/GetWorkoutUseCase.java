package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.GetWorkoutRequest;
import com.fontys.fitquest.domain.responses.GetWorkoutResponse;

public interface GetWorkoutUseCase {
    GetWorkoutResponse getWorkout(GetWorkoutRequest request);
}