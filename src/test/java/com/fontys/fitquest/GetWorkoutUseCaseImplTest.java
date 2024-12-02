package com.fontys.fitquest;

import com.fontys.fitquest.business.exception.InvalidWorkoutPlanException;
import com.fontys.fitquest.business.implementation.GetWorkoutUseCaseImpl;
import com.fontys.fitquest.domain.requests.GetWorkoutRequest;
import com.fontys.fitquest.domain.responses.GetWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetWorkoutUseCaseImplTest {

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @InjectMocks
    private GetWorkoutUseCaseImpl getWorkoutUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWorkout_validId_returnsWorkout() {
        // Arrange
        long workoutId = 1L;
        WorkoutPlanEntity workoutPlanEntity = new WorkoutPlanEntity();
        workoutPlanEntity.setId(workoutId);
        when(workoutPlanRepository.findById(workoutId)).thenReturn(Optional.of(workoutPlanEntity));

        // Act
        GetWorkoutResponse response = getWorkoutUseCase.getWorkout(new GetWorkoutRequest(workoutId));

        // Assert
        assertNotNull(response);
        assertEquals(workoutId, response.getWorkoutPlan().getId());
    }

    @Test
    void getWorkout_invalidId_throwsException() {
        // Arrange
        long workoutId = 1L;
        when(workoutPlanRepository.findById(workoutId)).thenReturn(Optional.empty());

        // Act & Assert
        GetWorkoutRequest request = new GetWorkoutRequest(workoutId);
        assertThrows(InvalidWorkoutPlanException.class, () -> getWorkoutUseCase.getWorkout(request));
    }
}