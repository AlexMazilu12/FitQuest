package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.DeleteExerciseFromWorkoutUseCase;
import com.fontys.fitquest.domain.requests.DeleteExerciseFromWorkoutRequest;
import com.fontys.fitquest.domain.responses.DeleteExerciseFromWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteExerciseFromWorkoutUseCaseImpl implements DeleteExerciseFromWorkoutUseCase {
    private final WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @Override
    public DeleteExerciseFromWorkoutResponse deleteExerciseFromWorkout(DeleteExerciseFromWorkoutRequest request) {
        workoutPlanExerciseRepository.deleteByWorkoutPlanIdAndExerciseId(request.getWorkoutPlanId(), request.getExerciseId());
        return DeleteExerciseFromWorkoutResponse.builder()
                .message("Exercise removed from workout plan successfully")
                .build();
    }
}