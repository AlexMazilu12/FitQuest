package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.CreateWorkoutPlanUseCase;
import com.fontys.fitquest.business.exception.WorkoutPlanTitleAlreadyExistsException;
import com.fontys.fitquest.domain.requests.CreateWorkoutPlanRequest;
import com.fontys.fitquest.domain.responses.CreateWorkoutPlanResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateWorkoutPlanUseCaseImpl implements CreateWorkoutPlanUseCase {
    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    public CreateWorkoutPlanResponse createWorkoutPlan(CreateWorkoutPlanRequest request) {
        if (workoutPlanRepository.existsByUserIdAndTitle(request.getUserId(), request.getTitle())) {
            throw new WorkoutPlanTitleAlreadyExistsException();
        }

        WorkoutPlanEntity savedWorkoutPlan = saveNewWorkoutPlan(request);

        return CreateWorkoutPlanResponse.builder()
                .id(savedWorkoutPlan.getId())
                .userId(savedWorkoutPlan.getUserId())
                .title(savedWorkoutPlan.getTitle())
                .description(savedWorkoutPlan.getDescription())
                .createdAt(savedWorkoutPlan.getCreatedAt())
                .updatedAt(savedWorkoutPlan.getUpdatedAt())
                .build();
    }

    private WorkoutPlanEntity saveNewWorkoutPlan(CreateWorkoutPlanRequest request) {
        WorkoutPlanEntity newWorkoutPlan = WorkoutPlanEntity.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return workoutPlanRepository.save(newWorkoutPlan);
    }
}
