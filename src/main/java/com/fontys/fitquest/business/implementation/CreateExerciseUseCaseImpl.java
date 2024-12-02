package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.CreateExerciseUseCase;
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