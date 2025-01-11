package com.fontys.fitquest.business;

import com.fontys.fitquest.business.exception.ExerciseNotFoundException;
import com.fontys.fitquest.business.implementation.GetExerciseUseCaseImpl;
import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class GetExerciseUseCaseImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private GetExerciseUseCaseImpl getExerciseUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getExercise() {
        ExerciseEntity exerciseEntity = ExerciseEntity.builder()
                .id(1)
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .build();

        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exerciseEntity));

        Exercise exercise = getExerciseUseCase.getExercise(1);

        assertEquals(1, exercise.getId());
        assertEquals("Push Up", exercise.getName());
        assertEquals(MuscleGroup.CHEST, exercise.getMuscleGroup());
        assertEquals("A basic push up exercise", exercise.getDescription());
    }

    @Test
    void getExercise_notFound() {
        when(exerciseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ExerciseNotFoundException.class, () -> getExerciseUseCase.getExercise(1));
    }
}