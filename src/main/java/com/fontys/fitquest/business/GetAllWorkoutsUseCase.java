package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.GetAllWorkoutsRequest;
import com.fontys.fitquest.domain.responses.GetAllWorkoutsResponse;

public interface GetAllWorkoutsUseCase {
    GetAllWorkoutsResponse getAllWorkouts(GetAllWorkoutsRequest request);
}
