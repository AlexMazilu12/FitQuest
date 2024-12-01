package com.fontys.fitquest;

import com.fontys.fitquest.business.GetWorkoutUseCase;
import com.fontys.fitquest.controller.WorkoutController;
import com.fontys.fitquest.domain.WorkoutPlan;
import com.fontys.fitquest.domain.requests.GetWorkoutRequest;
import com.fontys.fitquest.domain.responses.GetWorkoutResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class WorkoutControllerTest {

    @Mock
    private GetWorkoutUseCase getWorkoutUseCase;

    @InjectMocks
    private WorkoutController workoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWorkout_validId_returnsWorkout() {
        // Arrange
        long workoutId = 1L;
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setId(workoutId);
        GetWorkoutResponse response = GetWorkoutResponse.builder().workoutPlan(workoutPlan).build();
        when(getWorkoutUseCase.getWorkout(any(GetWorkoutRequest.class))).thenReturn(response);

        // Act
        ResponseEntity<WorkoutPlan> result = workoutController.getWorkout(workoutId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(workoutId, result.getBody().getId());
    }

    @Test
    void getWorkout_invalidId_returnsNotFound() {
        // Arrange
        long workoutId = 1L;
        GetWorkoutResponse response = GetWorkoutResponse.builder().workoutPlan(null).build();
        when(getWorkoutUseCase.getWorkout(any(GetWorkoutRequest.class))).thenReturn(response);

        // Act
        ResponseEntity<WorkoutPlan> result = workoutController.getWorkout(workoutId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}