package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.UpdateExerciseInWorkoutRequest;
import com.fontys.fitquest.domain.responses.UpdateExerciseInWorkoutResponse;

public interface UpdateExerciseInWorkoutUseCase {
    UpdateExerciseInWorkoutResponse updateExerciseInWorkout(UpdateExerciseInWorkoutRequest request);
}