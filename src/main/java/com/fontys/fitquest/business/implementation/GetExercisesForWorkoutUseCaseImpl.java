package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetExercisesForWorkoutUseCase;
import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.domain.requests.GetExercisesForWorkoutRequest;
import com.fontys.fitquest.domain.responses.GetExercisesForWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetExercisesForWorkoutUseCaseImpl implements GetExercisesForWorkoutUseCase {
    private final WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @Override
    public GetExercisesForWorkoutResponse getExercisesForWorkout(GetExercisesForWorkoutRequest request) {
        List<WorkoutPlanExerciseEntity> workoutPlanExerciseEntities = workoutPlanExerciseRepository.findByWorkoutPlanId(request.getWorkoutPlanId());
        List<Exercise> exercises = workoutPlanExerciseEntities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
        return GetExercisesForWorkoutResponse.builder()
                .exercises(exercises)
                .build();
    }

    private Exercise convertToDomain(WorkoutPlanExerciseEntity workoutPlanExerciseEntity) {
        return Exercise.builder()
                .id(workoutPlanExerciseEntity.getExercise().getId())
                .name(workoutPlanExerciseEntity.getExercise().getName())
                .muscleGroup(workoutPlanExerciseEntity.getExercise().getMuscleGroup())
                .description(workoutPlanExerciseEntity.getExercise().getDescription())
                .createdAt(workoutPlanExerciseEntity.getExercise().getCreatedAt())
                .build();
    }
}