package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.DeleteExerciseFromWorkoutUseCaseImpl;
import com.fontys.fitquest.domain.requests.DeleteExerciseFromWorkoutRequest;
import com.fontys.fitquest.domain.responses.DeleteExerciseFromWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class DeleteExerciseFromWorkoutUseCaseImplTest {

    @Mock
    private WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @InjectMocks
    private DeleteExerciseFromWorkoutUseCaseImpl deleteExerciseFromWorkoutUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteExerciseFromWorkout() {
        DeleteExerciseFromWorkoutRequest request = new DeleteExerciseFromWorkoutRequest();
        request.setWorkoutPlanId(1L);
        request.setExerciseId(1L);

        doNothing().when(workoutPlanExerciseRepository).deleteByWorkoutPlanIdAndExerciseId(1L, 1L);

        DeleteExerciseFromWorkoutResponse response = deleteExerciseFromWorkoutUseCase.deleteExerciseFromWorkout(request);

        assertEquals("Exercise removed from workout plan successfully", response.getMessage());
        verify(workoutPlanExerciseRepository).deleteByWorkoutPlanIdAndExerciseId(1L, 1L);
    }
}