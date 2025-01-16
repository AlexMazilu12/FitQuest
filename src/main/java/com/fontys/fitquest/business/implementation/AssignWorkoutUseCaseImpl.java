package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.AssignWorkoutUseCase;
import com.fontys.fitquest.domain.requests.AssignWorkoutRequest;
import com.fontys.fitquest.domain.responses.AssignWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AssignWorkoutUseCaseImpl implements AssignWorkoutUseCase {
    private final WorkoutPlanRepository workoutPlanRepository;
    private final WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @Override
    public AssignWorkoutResponse assignWorkout(AssignWorkoutRequest request) {
        WorkoutPlanEntity workoutPlan = workoutPlanRepository.findById(request.getWorkoutId())
                .orElseThrow(() -> new IllegalArgumentException("Workout plan not found"));

        WorkoutPlanEntity userWorkoutPlan = WorkoutPlanEntity.builder()
                .userId(request.getUserId())
                .title(workoutPlan.getTitle())
                .description(workoutPlan.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        workoutPlanRepository.save(userWorkoutPlan);

        List<WorkoutPlanExerciseEntity> exercises = workoutPlanExerciseRepository.findByWorkoutPlanId(workoutPlan.getId());
        for (WorkoutPlanExerciseEntity exercise : exercises) {
            WorkoutPlanExerciseEntity userExercise = WorkoutPlanExerciseEntity.builder()
                    .workoutPlan(userWorkoutPlan)
                    .exercise(exercise.getExercise())
                    .sets(exercise.getSets())
                    .reps(exercise.getReps())
                    .restTime(exercise.getRestTime())
                    .build();
            workoutPlanExerciseRepository.save(userExercise);
        }

        AssignWorkoutResponse response = new AssignWorkoutResponse();
        response.setWorkoutId(userWorkoutPlan.getId());
        response.setUserId(userWorkoutPlan.getUserId()); // Ensure userId is set correctly
        response.setMessage("Workout and exercises assigned successfully");
        return response;
    }
}