// src/main/java/com/fontys/fitquest/business/implementation/GetExerciseUseCaseImpl.java
package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetExerciseUseCase;
import com.fontys.fitquest.business.exception.ExerciseNotFoundException;
import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetExerciseUseCaseImpl implements GetExerciseUseCase {
    private final ExerciseRepository exerciseRepository;

    @Override
    public Exercise getExercise(int id) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(id);
        if (exerciseEntityOptional.isPresent()) {
            return convertToDomain(exerciseEntityOptional.get());
        } else {
            throw new ExerciseNotFoundException("Exercise not found with id: " + id);
        }
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