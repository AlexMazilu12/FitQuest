package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetAllExercisesUseCase;
import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.domain.MuscleGroup;
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
    public List<Exercise> getAllExercises(MuscleGroup muscleGroup, String orderBy, String direction) {
        return exerciseRepository.findByFilters(muscleGroup, orderBy, direction).stream()
                .map(ExerciseConverter::convert)
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
