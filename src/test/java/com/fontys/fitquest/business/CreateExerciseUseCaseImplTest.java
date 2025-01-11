package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.CreateExerciseUseCaseImpl;
import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.domain.requests.CreateExerciseRequest;
import com.fontys.fitquest.domain.responses.CreateExerciseResponse;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateExerciseUseCaseImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private CreateExerciseUseCaseImpl createExerciseUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExercise() {
        CreateExerciseRequest request = CreateExerciseRequest.builder()
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .build();

        ExerciseEntity savedExercise = ExerciseEntity.builder()
                .id(1)
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .createdAt(LocalDateTime.now())
                .build();

        when(exerciseRepository.save(any(ExerciseEntity.class))).thenReturn(savedExercise);

        CreateExerciseResponse response = createExerciseUseCase.createExercise(request);

        assertEquals(1, response.getId());
        assertEquals("Push Up", response.getName());
        assertEquals("CHEST", response.getMuscleGroup());
        assertEquals("A basic push up exercise", response.getDescription());
    }
}