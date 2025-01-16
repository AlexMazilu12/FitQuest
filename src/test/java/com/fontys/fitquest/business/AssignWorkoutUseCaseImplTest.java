package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.AssignWorkoutUseCaseImpl;
import com.fontys.fitquest.domain.requests.AssignWorkoutRequest;
import com.fontys.fitquest.domain.responses.AssignWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AssignWorkoutUseCaseImplTest {

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @Mock
    private WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @InjectMocks
    private AssignWorkoutUseCaseImpl assignWorkoutUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssignWorkout_Success() {
        // Arrange
        AssignWorkoutRequest request = new AssignWorkoutRequest();
        request.setWorkoutId(1L);
        request.setUserId(2);

        WorkoutPlanEntity workoutPlan = WorkoutPlanEntity.builder()
                .id(1L)
                .userId(1)
                .title("Workout Plan 1")
                .description("Description 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        WorkoutPlanExerciseEntity exercise1 = WorkoutPlanExerciseEntity.builder()
                .id(1L)
                .workoutPlan(workoutPlan)
                .exercise(null) // Mock exercise entity
                .sets(3)
                .reps(10)
                .restTime(60)
                .build();

        WorkoutPlanExerciseEntity exercise2 = WorkoutPlanExerciseEntity.builder()
                .id(2L)
                .workoutPlan(workoutPlan)
                .exercise(null) // Mock exercise entity
                .sets(4)
                .reps(12)
                .restTime(90)
                .build();

        List<WorkoutPlanExerciseEntity> exercises = Arrays.asList(exercise1, exercise2);

        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.of(workoutPlan));
        when(workoutPlanRepository.save(any(WorkoutPlanEntity.class))).thenAnswer(invocation -> {
            WorkoutPlanEntity savedWorkoutPlan = invocation.getArgument(0);
            savedWorkoutPlan.setId(2L); // Mock the ID generation
            return savedWorkoutPlan;
        });
        when(workoutPlanExerciseRepository.findByWorkoutPlanId(1L)).thenReturn(exercises);

        // Act
        AssignWorkoutResponse response = assignWorkoutUseCase.assignWorkout(request);

        // Assert
        assertEquals(2L, response.getWorkoutId());
        assertEquals(2, response.getUserId());
        assertEquals("Workout and exercises assigned successfully", response.getMessage());

        verify(workoutPlanRepository, times(1)).findById(1L);
        verify(workoutPlanRepository, times(1)).save(any(WorkoutPlanEntity.class));
        verify(workoutPlanExerciseRepository, times(1)).findByWorkoutPlanId(1L);
        verify(workoutPlanExerciseRepository, times(2)).save(any(WorkoutPlanExerciseEntity.class));
    }

    @Test
    void testAssignWorkout_WorkoutPlanNotFound() {
        // Arrange
        AssignWorkoutRequest request = new AssignWorkoutRequest();
        request.setWorkoutId(1L);
        request.setUserId(2);

        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> assignWorkoutUseCase.assignWorkout(request));

        verify(workoutPlanRepository, times(1)).findById(1L);
        verify(workoutPlanRepository, never()).save(any(WorkoutPlanEntity.class));
        verify(workoutPlanExerciseRepository, never()).findByWorkoutPlanId(anyLong());
        verify(workoutPlanExerciseRepository, never()).save(any(WorkoutPlanExerciseEntity.class));
    }
}