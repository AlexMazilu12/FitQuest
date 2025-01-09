package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.AddExerciseToWorkoutUseCase;
import com.fontys.fitquest.domain.requests.AddExerciseToWorkoutRequest;
import com.fontys.fitquest.domain.responses.AddExerciseToWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddExerciseToWorkoutUseCaseImpl implements AddExerciseToWorkoutUseCase {
    private final WorkoutPlanExerciseRepository workoutPlanExerciseRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    public AddExerciseToWorkoutResponse addExerciseToWorkout(AddExerciseToWorkoutRequest request) {
        WorkoutPlanEntity workoutPlan = workoutPlanRepository.findById(request.getWorkoutPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Workout plan not found"));

        WorkoutPlanExerciseEntity workoutPlanExercise = WorkoutPlanExerciseEntity.builder()
                .workoutPlan(workoutPlan)
                .exercise(request.getExercise())
                .sets(request.getSets())
                .reps(request.getReps())
                .restTime(request.getRestTime())
                .build();

        workoutPlanExerciseRepository.save(workoutPlanExercise);

        return AddExerciseToWorkoutResponse.builder()
                .id(workoutPlanExercise.getId())
                .build();
    }
}