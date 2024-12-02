package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.Exercise;

public interface GetExerciseUseCase {
    Exercise getExercise(int id);
}