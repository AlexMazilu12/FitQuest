package com.fontys.fitquest.business;

import com.fontys.fitquest.business.exception.InvalidWorkoutPlanException;
import com.fontys.fitquest.business.implementation.DeleteWorkoutPlanUseCaseImpl;
import com.fontys.fitquest.domain.requests.DeleteWorkoutPlanRequest;
import com.fontys.fitquest.domain.responses.DeleteWorkoutPlanResponse;
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

class DeleteWorkoutPlanUseCaseImplTest {

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @InjectMocks
    private DeleteWorkoutPlanUseCaseImpl deleteWorkoutPlanUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteWorkoutPlan_validId_returnsResponse() {
        // Arrange
        long workoutId = 1L;
        WorkoutPlanEntity workoutPlanEntity = new WorkoutPlanEntity();
        workoutPlanEntity.setId(workoutId);
        when(workoutPlanRepository.findById(workoutId)).thenReturn(Optional.of(workoutPlanEntity));

        // Act
        DeleteWorkoutPlanResponse response = deleteWorkoutPlanUseCase.deleteWorkoutPlan(new DeleteWorkoutPlanRequest(workoutId, 0));

        // Assert
        assertNotNull(response);
        assertTrue(response.isSuccess());
    }

    @Test
    void deleteWorkoutPlan_invalidId_throwsException() {
        // Arrange
        long workoutId = 1L;
        when(workoutPlanRepository.findById(workoutId)).thenReturn(Optional.empty());

// Act & Assert
        DeleteWorkoutPlanRequest request = new DeleteWorkoutPlanRequest(workoutId, 0);
        assertThrows(InvalidWorkoutPlanException.class, () -> deleteWorkoutPlanUseCase.deleteWorkoutPlan(request));
    }
}