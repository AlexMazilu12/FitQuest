package com.fontys.fitquest.business;

import com.fontys.fitquest.business.exception.WorkoutPlanTitleAlreadyExistsException;
import com.fontys.fitquest.business.implementation.CreateWorkoutPlanUseCaseImpl;
import com.fontys.fitquest.domain.requests.CreateWorkoutPlanRequest;
import com.fontys.fitquest.domain.responses.CreateWorkoutPlanResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateWorkoutPlanUseCaseImplTest {

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @InjectMocks
    private CreateWorkoutPlanUseCaseImpl createWorkoutPlanUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWorkoutPlan_validRequest_returnsResponse() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        CreateWorkoutPlanRequest request = new CreateWorkoutPlanRequest(1, "Title", "Description", now, now);
        WorkoutPlanEntity workoutPlanEntity = new WorkoutPlanEntity();
        workoutPlanEntity.setId(1L);
        when(workoutPlanRepository.existsByUserIdAndTitle(1, "Title")).thenReturn(false);
        when(workoutPlanRepository.save(any(WorkoutPlanEntity.class))).thenReturn(workoutPlanEntity);

        // Act
        CreateWorkoutPlanResponse response = createWorkoutPlanUseCase.createWorkoutPlan(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void createWorkoutPlan_duplicateTitle_throwsException() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        CreateWorkoutPlanRequest request = new CreateWorkoutPlanRequest(1, "Title", "Description", now, now);
        when(workoutPlanRepository.existsByUserIdAndTitle(1, "Title")).thenReturn(true);

        // Act & Assert
        assertThrows(WorkoutPlanTitleAlreadyExistsException.class, () -> {
            createWorkoutPlanUseCase.createWorkoutPlan(request);
        });
    }
}