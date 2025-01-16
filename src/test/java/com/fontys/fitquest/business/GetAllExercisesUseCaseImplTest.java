package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.GetAllExercisesUseCaseImpl;
import com.fontys.fitquest.domain.Exercise;
import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAllExercisesUseCaseImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private GetAllExercisesUseCaseImpl getAllExercisesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllExercises() {
        ExerciseEntity exercise1 = ExerciseEntity.builder()
                .id(1)
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .build();

        ExerciseEntity exercise2 = ExerciseEntity.builder()
                .id(2)
                .name("Squat")
                .muscleGroup(MuscleGroup.LEGS)
                .description("A basic squat exercise")
                .build();

        when(exerciseRepository.findByFilters(null, "name", "asc", "basic")).thenReturn(Arrays.asList(exercise1, exercise2));

        List<Exercise> exercises = getAllExercisesUseCase.getAllExercises(null, "name", "asc", "basic");

        assertEquals(2, exercises.size());
        assertEquals("Push Up", exercises.get(0).getName());
        assertEquals("Squat", exercises.get(1).getName());
    }
}