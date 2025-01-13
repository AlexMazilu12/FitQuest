package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.AddExerciseToWorkoutUseCaseImpl;
import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.domain.requests.AddExerciseToWorkoutRequest;
import com.fontys.fitquest.domain.responses.AddExerciseToWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AddExerciseToWorkoutUseCaseImplTest {

    @Mock
    private WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @InjectMocks
    private AddExerciseToWorkoutUseCaseImpl addExerciseToWorkoutUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddExerciseToWorkout() {
        WorkoutPlanEntity workoutPlanEntity = WorkoutPlanEntity.builder()
                .id(1L)
                .userId(1)
                .title("Workout Plan")
                .description("Description")
                .build();

        Exercise exercise = Exercise.builder()
                .id(1)
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .build();

        ExerciseEntity exerciseEntity = ExerciseEntity.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .muscleGroup(exercise.getMuscleGroup())
                .description(exercise.getDescription())
                .createdAt(exercise.getCreatedAt())
                .build();

        AddExerciseToWorkoutRequest request = AddExerciseToWorkoutRequest.builder()
                .workoutPlanId(1L)
                .exercise(exerciseEntity)
                .sets(3)
                .reps(10)
                .restTime(60)
                .build();

        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.of(workoutPlanEntity));

        WorkoutPlanExerciseEntity workoutPlanExerciseEntity = WorkoutPlanExerciseEntity.builder()
                .workoutPlan(workoutPlanEntity)
                .exercise(exerciseEntity)
                .sets(3)
                .reps(10)
                .restTime(60)
                .build();

        when(workoutPlanExerciseRepository.save(any(WorkoutPlanExerciseEntity.class))).thenReturn(workoutPlanExerciseEntity);

        AddExerciseToWorkoutResponse response = addExerciseToWorkoutUseCase.addExerciseToWorkout(request);

        assertEquals(workoutPlanExerciseEntity.getId(), response.getId());
        verify(workoutPlanRepository).findById(1L);
        verify(workoutPlanExerciseRepository).save(any(WorkoutPlanExerciseEntity.class));
    }

    @Test
    void testAddExerciseToWorkout_WorkoutPlanNotFound() {
        ExerciseEntity exerciseEntity = ExerciseEntity.builder()
                .id(1)
                .build();

        AddExerciseToWorkoutRequest request = AddExerciseToWorkoutRequest.builder()
                .workoutPlanId(1L)
                .exercise(exerciseEntity)
                .sets(3)
                .reps(10)
                .restTime(60)
                .build();

        when(workoutPlanRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> addExerciseToWorkoutUseCase.addExerciseToWorkout(request));
        verify(workoutPlanRepository).findById(1L);
        verify(workoutPlanExerciseRepository, never()).save(any(WorkoutPlanExerciseEntity.class));
    }
}