package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.DeleteExerciseFromWorkoutRequest;
import com.fontys.fitquest.domain.responses.DeleteExerciseFromWorkoutResponse;

public interface DeleteExerciseFromWorkoutUseCase {
    DeleteExerciseFromWorkoutResponse deleteExerciseFromWorkout(DeleteExerciseFromWorkoutRequest request);
}