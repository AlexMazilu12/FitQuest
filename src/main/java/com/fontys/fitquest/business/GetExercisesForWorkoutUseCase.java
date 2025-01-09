package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.domain.requests.GetExercisesForWorkoutRequest;

import java.util.List;

public interface GetExercisesForWorkoutUseCase {
    List<Exercise> getExercisesForWorkout(GetExercisesForWorkoutRequest request);
}