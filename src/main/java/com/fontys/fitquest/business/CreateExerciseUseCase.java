package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.CreateExerciseRequest;
import com.fontys.fitquest.domain.responses.CreateExerciseResponse;

public interface CreateExerciseUseCase {
    CreateExerciseResponse createExercise(CreateExerciseRequest request);
}