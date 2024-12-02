package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.Exercise;
import java.util.List;

public interface GetAllExercisesUseCase {
    List<Exercise> getAllExercises();
}