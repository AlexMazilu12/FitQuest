// src/test/java/com/fontys/fitquest/controller/WorkoutControllerIntegrationTest.java

package com.fontys.fitquest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.domain.requests.CreateWorkoutPlanRequest;
import com.fontys.fitquest.domain.requests.UpdateWorkoutPlanRequest;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WorkoutControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private UserRepository userRepository;

    private Long userId;

    @BeforeEach
    void setup() {
        workoutPlanRepository.deleteAll();

        // Find an existing user or create a new one
        Optional<UserEntity> optionalUser = userRepository.findAll().stream().findFirst();
        if (optionalUser.isPresent()) {
            userId = optionalUser.get().getId();
        } else {
            UserEntity newUser = UserEntity.builder()
                    .email("default@example.com")
                    .password("password")
                    .role(Role.USER)
                    .name("Default User")
                    .build();
            userId = userRepository.save(newUser).getId();
        }
    }

    @Test
    @WithMockUser(roles = "USER")
    void testCreateWorkout() throws Exception {
        CreateWorkoutPlanRequest request = new CreateWorkoutPlanRequest(userId.intValue(), "Workout Plan 1", "Description 1", null, null);

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Workout Plan 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testUpdateWorkout() throws Exception {
        WorkoutPlanEntity workoutPlan = WorkoutPlanEntity.builder()
                .userId(userId.intValue())
                .title("Workout Plan 1")
                .description("Description 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        workoutPlan = workoutPlanRepository.save(workoutPlan);

        UpdateWorkoutPlanRequest request = new UpdateWorkoutPlanRequest(workoutPlan.getId(), userId.intValue(), "Updated Workout Plan", "Updated Description");

        mockMvc.perform(put("/workouts/" + workoutPlan.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testDeleteWorkout() throws Exception {
        WorkoutPlanEntity workoutPlan = WorkoutPlanEntity.builder()
                .userId(userId.intValue())
                .title("Workout Plan 1")
                .description("Description 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        workoutPlan = workoutPlanRepository.save(workoutPlan);

        mockMvc.perform(delete("/workouts/" + workoutPlan.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllWorkouts() throws Exception {
        WorkoutPlanEntity workoutPlan = WorkoutPlanEntity.builder()
                .userId(userId.intValue())
                .title("Workout Plan 1")
                .description("Description 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        workoutPlanRepository.save(workoutPlan);

        mockMvc.perform(get("/workouts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.workoutPlans[0].title").value("Workout Plan 1"))
                .andExpect(jsonPath("$.workoutPlans[0].description").value("Description 1"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetWorkout() throws Exception {
        WorkoutPlanEntity workoutPlan = WorkoutPlanEntity.builder()
                .userId(userId.intValue())
                .title("Workout Plan 1")
                .description("Description 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        workoutPlan = workoutPlanRepository.save(workoutPlan);

        mockMvc.perform(get("/workouts/" + workoutPlan.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Workout Plan 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));
    }

    // Validation Tests //
    @Test
    @WithMockUser(roles = "USER")
    void testCreateWorkoutValidation() throws Exception {
        CreateWorkoutPlanRequest request = new CreateWorkoutPlanRequest(1, "", "Sample description", LocalDateTime.now(), LocalDateTime.now());

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("must not be blank"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testUpdateWorkoutValidation() throws Exception {
        UpdateWorkoutPlanRequest request = new UpdateWorkoutPlanRequest(1L, 1, "", null);

        mockMvc.perform(put("/workouts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    // Error Handling Tests //
    @Test
    @WithMockUser(roles = "USER")
    void testGetWorkoutNotFound() throws Exception {
        mockMvc.perform(get("/workouts/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testUpdateWorkoutWithNonExistentId() throws Exception {
        UpdateWorkoutPlanRequest request = new UpdateWorkoutPlanRequest(999L, 1, "Updated Workout Plan", "Updated Description");

        mockMvc.perform(put("/workouts/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testDeleteWorkoutWithNonExistentId() throws Exception {
        mockMvc.perform(delete("/workouts/999"))
                .andExpect(status().isNotFound());
    }

    // Security Tests //
    @Test
    void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateWorkoutPlanRequest(1, "Workout Plan 1", "Description 1", null, null))))
                .andExpect(status().isUnauthorized());
    }

    // Edge Cases //
    @Test
    @WithMockUser(roles = "USER")
    void testCreateWorkoutWithValidNumericDescription() throws Exception {
        // Simulate numeric description sent as a string
        String validRequest = String.format("{\"userId\": %d, \"title\": \"Workout Plan 1\", \"description\": \"123\"}", userId.intValue());

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequest))
                .andExpect(status().isCreated()); // Expect 201 Created
    }

    @Test
    @WithMockUser(roles = "USER")
    void testCreateDuplicateWorkout() throws Exception {
        CreateWorkoutPlanRequest request = new CreateWorkoutPlanRequest(userId.intValue(), "Workout Plan 1", "Description 1", null, null);

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testConcurrentUpdateWorkout() throws Exception {
        WorkoutPlanEntity workoutPlan = WorkoutPlanEntity.builder()
                .userId(userId.intValue())
                .title("Workout Plan 1")
                .description("Description 1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        workoutPlan = workoutPlanRepository.save(workoutPlan);

        UpdateWorkoutPlanRequest request1 = new UpdateWorkoutPlanRequest(workoutPlan.getId(), userId.intValue(), "Updated Workout Plan 1", "Updated Description 1");
        UpdateWorkoutPlanRequest request2 = new UpdateWorkoutPlanRequest(workoutPlan.getId(), userId.intValue(), "Updated Workout Plan 2", "Updated Description 2");

        mockMvc.perform(put("/workouts/" + workoutPlan.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request1)))
                .andExpect(status().isNoContent());

        mockMvc.perform(put("/workouts/" + workoutPlan.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request2)))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testCreateWorkoutResponseContent() throws Exception {
        CreateWorkoutPlanRequest request = new CreateWorkoutPlanRequest(userId.intValue(), "Workout Plan 1", "Description 1", null, null);

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Workout Plan 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.id").exists());
    }
}