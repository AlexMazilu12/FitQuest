package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.UpdateExerciseUseCase;
import com.fontys.fitquest.business.exception.ExerciseNotFoundException;
import com.fontys.fitquest.domain.requests.UpdateExerciseRequest;
import com.fontys.fitquest.domain.responses.UpdateExerciseResponse;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateExerciseUseCaseImpl implements UpdateExerciseUseCase {
    private final ExerciseRepository exerciseRepository;

    @Override
    public UpdateExerciseResponse updateExercise(UpdateExerciseRequest request) {
        if (request.getName() == null || request.getName().isEmpty() ||
                request.getMuscleGroup() == null || request.getDescription() == null) {
            throw new IllegalArgumentException("Invalid input data");
        }

        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(request.getId());
        if (exerciseEntityOptional.isPresent()) {
            ExerciseEntity exerciseEntity = exerciseEntityOptional.get();
            exerciseEntity.setName(request.getName());
            exerciseEntity.setMuscleGroup(request.getMuscleGroup());
            exerciseEntity.setDescription(request.getDescription());
            if (exerciseEntity.getCreatedAt() == null) {
                exerciseEntity.setCreatedAt(LocalDateTime.now());
            }
            exerciseRepository.save(exerciseEntity);

            return UpdateExerciseResponse.builder()
                    .id(exerciseEntity.getId())
                    .name(exerciseEntity.getName())
                    .muscleGroup(exerciseEntity.getMuscleGroup().name())
                    .description(exerciseEntity.getDescription())
                    .build();
        } else {
            throw new ExerciseNotFoundException("Exercise not found");
        }
    }
}