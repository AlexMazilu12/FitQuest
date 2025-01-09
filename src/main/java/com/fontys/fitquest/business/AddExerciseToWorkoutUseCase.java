package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.AddExerciseToWorkoutRequest;
import com.fontys.fitquest.domain.responses.AddExerciseToWorkoutResponse;

public interface AddExerciseToWorkoutUseCase {
    AddExerciseToWorkoutResponse addExerciseToWorkout(AddExerciseToWorkoutRequest request);
}