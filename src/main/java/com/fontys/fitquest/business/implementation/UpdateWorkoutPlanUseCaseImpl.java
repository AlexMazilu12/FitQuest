package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.UpdateWorkoutPlanUseCase;
import com.fontys.fitquest.business.exception.InvalidWorkoutPlan;
import com.fontys.fitquest.domain.UpdateWorkoutPlanRequest;
import com.fontys.fitquest.domain.UpdateWorkoutPlanResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateWorkoutPlanUseCaseImpl implements UpdateWorkoutPlanUseCase {
    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    public UpdateWorkoutPlanResponse updateWorkoutPlan(UpdateWorkoutPlanRequest request) {
        Optional<WorkoutPlanEntity> optionalWorkoutPlan = workoutPlanRepository.findById(request.getId());

        if (optionalWorkoutPlan.isEmpty()) {
            throw new InvalidWorkoutPlan("Workout plan not found");
        }

        WorkoutPlanEntity workoutPlan = optionalWorkoutPlan.get();
        workoutPlan.setTitle(request.getTitle());
        workoutPlan.setDescription(request.getDescription());
        workoutPlan.setUpdatedAt(LocalDateTime.now());

        WorkoutPlanEntity updatedWorkoutPlan = workoutPlanRepository.save(workoutPlan);

        return UpdateWorkoutPlanResponse.builder()
                .id(updatedWorkoutPlan.getId())
                .userId(updatedWorkoutPlan.getUserId())
                .title(updatedWorkoutPlan.getTitle())
                .description(updatedWorkoutPlan.getDescription())
                .updatedAt(updatedWorkoutPlan.getUpdatedAt())
                .build();
    }
}