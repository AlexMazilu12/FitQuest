package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.UpdateExerciseInWorkoutUseCase;
import com.fontys.fitquest.domain.requests.UpdateExerciseInWorkoutRequest;
import com.fontys.fitquest.domain.responses.UpdateExerciseInWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateExerciseInWorkoutUseCaseImpl implements UpdateExerciseInWorkoutUseCase {
    private final WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @Override
    public UpdateExerciseInWorkoutResponse updateExerciseInWorkout(UpdateExerciseInWorkoutRequest request) {
        WorkoutPlanExerciseEntity workoutPlanExercise = workoutPlanExerciseRepository.findByWorkoutPlanIdAndExerciseId(request.getWorkoutPlanId(), request.getExerciseId())
                .orElseThrow(() -> new IllegalArgumentException("Workout plan exercise not found"));

        workoutPlanExercise.setSets(request.getSets());
        workoutPlanExercise.setReps(request.getReps());
        workoutPlanExercise.setRestTime(request.getRestTime());

        workoutPlanExerciseRepository.save(workoutPlanExercise);

        return UpdateExerciseInWorkoutResponse.builder()
                .id(workoutPlanExercise.getId())
                .build();
    }
}