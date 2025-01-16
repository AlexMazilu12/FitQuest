package com.fontys.fitquest.business;


import com.fontys.fitquest.domain.requests.AssignWorkoutRequest;
import com.fontys.fitquest.domain.responses.AssignWorkoutResponse;

public interface AssignWorkoutUseCase {
    AssignWorkoutResponse assignWorkout(AssignWorkoutRequest request);
}