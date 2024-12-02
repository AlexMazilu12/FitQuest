package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.UpdateExerciseRequest;
import com.fontys.fitquest.domain.responses.UpdateExerciseResponse;

public interface UpdateExerciseUseCase {
    UpdateExerciseResponse updateExercise(UpdateExerciseRequest request);
}