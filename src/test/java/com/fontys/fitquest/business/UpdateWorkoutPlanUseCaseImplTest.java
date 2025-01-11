package com.fontys.fitquest.business;

import com.fontys.fitquest.business.exception.InvalidWorkoutPlanException;
import com.fontys.fitquest.business.implementation.UpdateWorkoutPlanUseCaseImpl;
import com.fontys.fitquest.domain.requests.UpdateWorkoutPlanRequest;
import com.fontys.fitquest.domain.responses.UpdateWorkoutPlanResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateWorkoutPlanUseCaseImplTest {

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @InjectMocks
    private UpdateWorkoutPlanUseCaseImpl updateWorkoutPlanUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateWorkoutPlan_success() {
        UpdateWorkoutPlanRequest request = new UpdateWorkoutPlanRequest(1L, 1, "New Title", "New Description");
        WorkoutPlanEntity workoutPlanEntity = new WorkoutPlanEntity();
        workoutPlanEntity.setId(1L);
        workoutPlanEntity.setUserId(1);
        workoutPlanEntity.setTitle("Old Title");
        workoutPlanEntity.setDescription("Old Description");
        workoutPlanEntity.setCreatedAt(LocalDateTime.now());
        workoutPlanEntity.setUpdatedAt(LocalDateTime.now());

        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.of(workoutPlanEntity));
        when(workoutPlanRepository.save(any(WorkoutPlanEntity.class))).thenReturn(workoutPlanEntity);

        UpdateWorkoutPlanResponse response = updateWorkoutPlanUseCase.updateWorkoutPlan(request);

        assertNotNull(response);
        assertEquals("New Title", response.getTitle());
        assertEquals("New Description", response.getDescription());
        verify(workoutPlanRepository, times(1)).findById(1L);
        verify(workoutPlanRepository, times(1)).save(any(WorkoutPlanEntity.class));
    }

    @Test
    void updateWorkoutPlan_notFound() {
        UpdateWorkoutPlanRequest request = new UpdateWorkoutPlanRequest(1L, 1, "New Title", "New Description");

        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(InvalidWorkoutPlanException.class, () -> updateWorkoutPlanUseCase.updateWorkoutPlan(request));
        verify(workoutPlanRepository, times(1)).findById(1L);
        verify(workoutPlanRepository, times(0)).save(any(WorkoutPlanEntity.class));
    }
}