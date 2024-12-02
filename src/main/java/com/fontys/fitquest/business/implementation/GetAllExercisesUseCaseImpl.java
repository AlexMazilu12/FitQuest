package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetAllExercisesUseCase;
import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllExercisesUseCaseImpl implements GetAllExercisesUseCase {
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> getAllExercises() {
        List<ExerciseEntity> exerciseEntities = exerciseRepository.findAll();
        return exerciseEntities.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    private Exercise convertToDomain(ExerciseEntity exerciseEntity) {
        return Exercise.builder()
                .id(exerciseEntity.getId())
                .name(exerciseEntity.getName())
                .muscleGroup(exerciseEntity.getMuscleGroup())
                .description(exerciseEntity.getDescription())
                .createdAt(exerciseEntity.getCreatedAt())
                .build();
    }
}
