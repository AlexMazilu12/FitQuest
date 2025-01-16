package com.fontys.fitquest.controller;

import com.fontys.fitquest.FitQuestApplication;
import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.WorkoutPlanExerciseRepository;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FitQuestApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StatisticsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private WorkoutPlanExerciseRepository workoutPlanExerciseRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @BeforeEach
    void setup() {
        workoutPlanRepository.deleteAll();
        workoutPlanExerciseRepository.deleteAll();

        WorkoutPlanEntity workoutPlan1 = WorkoutPlanEntity.builder()
                .title("Plan 1")
                .description("Description 1")
                .createdAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                .updatedAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                .userId(1)
                .build();
        workoutPlanRepository.save(workoutPlan1);

        WorkoutPlanEntity workoutPlan2 = WorkoutPlanEntity.builder()
                .title("Plan 2")
                .description("Description 2")
                .createdAt(LocalDateTime.of(2025, 2, 1, 0, 0))
                .updatedAt(LocalDateTime.of(2025, 2, 1, 0, 0))
                .userId(2)
                .build();
        workoutPlanRepository.save(workoutPlan2);

        ExerciseEntity exercise1 = ExerciseEntity.builder()
                .name("Exercise 1")
                .muscleGroup(MuscleGroup.CHEST)
                .description("Description 1")
                .createdAt(LocalDateTime.now())
                .build();
        exercise1 = exerciseRepository.save(exercise1); // Save the exercise

        ExerciseEntity exercise2 = ExerciseEntity.builder()
                .name("Exercise 2")
                .muscleGroup(MuscleGroup.BACK)
                .description("Description 2")
                .createdAt(LocalDateTime.now())
                .build();
        exercise2 = exerciseRepository.save(exercise2); // Save the exercise

        WorkoutPlanExerciseEntity workoutPlanExercise1 = WorkoutPlanExerciseEntity.builder()
                .workoutPlan(workoutPlan1)
                .exercise(exercise1)
                .reps(10)
                .sets(3)
                .restTime(60)
                .build();
        workoutPlanExerciseRepository.save(workoutPlanExercise1);

        WorkoutPlanExerciseEntity workoutPlanExercise2 = WorkoutPlanExerciseEntity.builder()
                .workoutPlan(workoutPlan2)
                .exercise(exercise2)
                .reps(12)
                .sets(4)
                .restTime(90)
                .build();
        workoutPlanExerciseRepository.save(workoutPlanExercise2);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetWorkoutsPerMonth() throws Exception {
        mockMvc.perform(get("/api/statistics/workouts-per-month")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].month", is(1)))
                .andExpect(jsonPath("$[0].count", is(1)))
                .andExpect(jsonPath("$[1].month", is(2)))
                .andExpect(jsonPath("$[1].count", is(1)));
    }
}