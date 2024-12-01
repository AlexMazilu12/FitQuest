package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.domain.WorkoutPlan;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;

public class WorkoutConverter {
    private WorkoutConverter() {
    }

    public static WorkoutPlan convert(WorkoutPlanEntity workoutPlanEntity) {
        return WorkoutPlan.builder()
                .id(workoutPlanEntity.getId())
                .userId(workoutPlanEntity.getUserId())
                .title(workoutPlanEntity.getTitle())
                .description(workoutPlanEntity.getDescription())
                .createdAt(workoutPlanEntity.getCreatedAt())
                .updatedAt(workoutPlanEntity.getUpdatedAt())
                .build();
    }
}