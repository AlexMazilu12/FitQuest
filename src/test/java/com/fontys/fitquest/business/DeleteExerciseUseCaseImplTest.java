package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.DeleteExerciseUseCaseImpl;
import com.fontys.fitquest.persistence.ExerciseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class DeleteExerciseUseCaseImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private DeleteExerciseUseCaseImpl deleteExerciseUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteExercise() {
        int exerciseId = 1;
        deleteExerciseUseCase.deleteExercise(exerciseId);
        verify(exerciseRepository).deleteById(exerciseId);
    }
}