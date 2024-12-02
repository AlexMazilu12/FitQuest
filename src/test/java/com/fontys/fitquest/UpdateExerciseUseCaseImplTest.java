package com.fontys.fitquest;

import com.fontys.fitquest.business.exception.ExerciseNotFoundException;
import com.fontys.fitquest.domain.requests.UpdateExerciseRequest;
import com.fontys.fitquest.domain.responses.UpdateExerciseResponse;
import com.fontys.fitquest.business.implementation.UpdateExerciseUseCaseImpl;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import com.fontys.fitquest.domain.MuscleGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UpdateExerciseUseCaseImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private UpdateExerciseUseCaseImpl updateExerciseUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateExercise() {
        UpdateExerciseRequest request = UpdateExerciseRequest.builder()
                .id(1)
                .name("Updated Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("An updated push up exercise")
                .build();

        ExerciseEntity existingExercise = ExerciseEntity.builder()
                .id(1)
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .build();

        when(exerciseRepository.findById(1)).thenReturn(Optional.of(existingExercise));
        when(exerciseRepository.save(any(ExerciseEntity.class))).thenReturn(existingExercise);

        UpdateExerciseResponse response = updateExerciseUseCase.updateExercise(request);

        assertEquals(1, response.getId());
        assertEquals("Updated Push Up", response.getName());
        assertEquals(MuscleGroup.CHEST, MuscleGroup.valueOf(response.getMuscleGroup()));
        assertEquals("An updated push up exercise", response.getDescription());
    }

    @Test
    void updateExercise_notFound() {
        UpdateExerciseRequest request = UpdateExerciseRequest.builder()
                .id(1)
                .name("Updated Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("An updated push up exercise")
                .build();

        when(exerciseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ExerciseNotFoundException.class, () -> updateExerciseUseCase.updateExercise(request));
    }
}