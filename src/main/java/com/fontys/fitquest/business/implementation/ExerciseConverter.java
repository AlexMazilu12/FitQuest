package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;

public class ExerciseConverter {
    private ExerciseConverter() {
    }

    public static Exercise convert(ExerciseEntity exerciseEntity) {
        return Exercise.builder()
                .id(exerciseEntity.getId())
                .name(exerciseEntity.getName())
                .muscleGroup(exerciseEntity.getMuscleGroup())
                .description(exerciseEntity.getDescription())
                .createdAt(exerciseEntity.getCreatedAt())
                .build();
    }
}