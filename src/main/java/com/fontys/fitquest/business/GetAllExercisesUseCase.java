package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.domain.MuscleGroup;

import java.util.List;

public interface GetAllExercisesUseCase {
    List<Exercise> getAllExercises(MuscleGroup muscleGroup, String orderBy, String direction);
}