package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.CreateExerciseUseCase;
import com.fontys.fitquest.business.exception.DuplicateExerciseException;
import com.fontys.fitquest.domain.requests.CreateExerciseRequest;
import com.fontys.fitquest.domain.responses.CreateExerciseResponse;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateExerciseUseCaseImpl implements CreateExerciseUseCase {
    private final ExerciseRepository exerciseRepository;

    @Override
    public CreateExerciseResponse createExercise(CreateExerciseRequest request) {
        if (request.getName() == null || request.getName().isEmpty() ||
                request.getMuscleGroup() == null || request.getDescription() == null) {
            throw new IllegalArgumentException("Invalid input data");
        }

        if (exerciseRepository.existsByName(request.getName())) {
            throw new DuplicateExerciseException("Exercise with the same name already exists");
        }

        ExerciseEntity savedExercise = saveNewExercise(request);

        return CreateExerciseResponse.builder()
                .id(savedExercise.getId())
                .name(savedExercise.getName())
                .muscleGroup(savedExercise.getMuscleGroup().name())
                .description(savedExercise.getDescription())
                .build();
    }

    private ExerciseEntity saveNewExercise(CreateExerciseRequest request) {
        ExerciseEntity newExercise = ExerciseEntity.builder()
                .name(request.getName())
                .muscleGroup(request.getMuscleGroup())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        return exerciseRepository.save(newExercise);
    }
}