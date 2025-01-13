package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.GetExercisesForWorkoutUseCaseImpl;
import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.domain.WorkoutExercise;
import com.fontys.fitquest.domain.requests.GetExercisesForWorkoutRequest;
import com.fontys.fitquest.domain.responses.GetExercisesForWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetExercisesForWorkoutUseCaseImplTest {

    @Mock
    private WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @InjectMocks
    private GetExercisesForWorkoutUseCaseImpl getExercisesForWorkoutUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetExercisesForWorkout() {
        GetExercisesForWorkoutRequest request = new GetExercisesForWorkoutRequest();
        request.setWorkoutPlanId(1L);

        ExerciseEntity exerciseEntity = ExerciseEntity.builder()
                .id(1)
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .build();

        WorkoutPlanExerciseEntity entity = WorkoutPlanExerciseEntity.builder()
                .exercise(exerciseEntity)
                .sets(3)
                .reps(10)
                .restTime(60)
                .build();

        when(workoutPlanExerciseRepository.findByWorkoutPlanId(1L)).thenReturn(Collections.singletonList(entity));

        GetExercisesForWorkoutResponse response = getExercisesForWorkoutUseCase.getExercisesForWorkout(request);

        List<WorkoutExercise> exercises = response.getExercises();
        assertEquals(1, exercises.size());
        assertEquals(3, exercises.get(0).getSets());
        assertEquals(10, exercises.get(0).getReps());
        assertEquals(60, exercises.get(0).getRestTime());
    }
}