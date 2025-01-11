package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.GetExercisesForWorkoutRequest;
import com.fontys.fitquest.domain.responses.GetExercisesForWorkoutResponse;

public interface GetExercisesForWorkoutUseCase {
    GetExercisesForWorkoutResponse getExercisesForWorkout(GetExercisesForWorkoutRequest request);
}