package com.fontys.fitquest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.domain.requests.CreateExerciseRequest;
import com.fontys.fitquest.domain.requests.UpdateExerciseRequest;
import com.fontys.fitquest.persistence.ExerciseRepository;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ExerciseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @BeforeEach
    public void setup() {
        exerciseRepository.deleteAll();
    }

    // CRUD Operations
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateExercise() throws Exception {
        CreateExerciseRequest request = new CreateExerciseRequest("Push Up", MuscleGroup.CHEST, "A basic push up exercise");

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Push Up"))
                .andExpect(jsonPath("$.muscleGroup").value("CHEST"))
                .andExpect(jsonPath("$.description").value("A basic push up exercise"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateExercise() throws Exception {
        ExerciseEntity exercise = ExerciseEntity.builder()
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .createdAt(LocalDateTime.now())
                .build();
        exercise = exerciseRepository.save(exercise);

        UpdateExerciseRequest request = new UpdateExerciseRequest(exercise.getId(), "Pull Up", MuscleGroup.BACK, "A basic pull up exercise");

        mockMvc.perform(put("/exercises/" + exercise.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pull Up"))
                .andExpect(jsonPath("$.muscleGroup").value("BACK"))
                .andExpect(jsonPath("$.description").value("A basic pull up exercise"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteExercise() throws Exception {
        ExerciseEntity exercise = ExerciseEntity.builder()
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .createdAt(LocalDateTime.now())
                .build();
        exercise = exerciseRepository.save(exercise);

        mockMvc.perform(delete("/exercises/" + exercise.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllExercises() throws Exception {
        ExerciseEntity exercise = ExerciseEntity.builder()
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .createdAt(LocalDateTime.now())
                .build();
        exerciseRepository.save(exercise);

        mockMvc.perform(get("/exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Push Up"))
                .andExpect(jsonPath("$[0].muscleGroup").value("CHEST"))
                .andExpect(jsonPath("$[0].description").value("A basic push up exercise"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetExercise() throws Exception {
        ExerciseEntity exercise = ExerciseEntity.builder()
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .createdAt(LocalDateTime.now())
                .build();
        exercise = exerciseRepository.save(exercise);

        mockMvc.perform(get("/exercises/" + exercise.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Push Up"))
                .andExpect(jsonPath("$.muscleGroup").value("CHEST"))
                .andExpect(jsonPath("$.description").value("A basic push up exercise"));
    }

    // Validation Tests
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateExerciseValidation() throws Exception {
        CreateExerciseRequest request = new CreateExerciseRequest("", MuscleGroup.CHEST, "");

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateExerciseValidation() throws Exception {
        UpdateExerciseRequest request = new UpdateExerciseRequest(1, "", MuscleGroup.CHEST, "");

        mockMvc.perform(put("/exercises/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateExerciseWithEmptyStrings() throws Exception {
        CreateExerciseRequest request = new CreateExerciseRequest("", MuscleGroup.CHEST, "");

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateExerciseWithNullValues() throws Exception {
        CreateExerciseRequest request = new CreateExerciseRequest(null, null, null);

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateExerciseWithBoundaryValues() throws Exception {
        CreateExerciseRequest request = new CreateExerciseRequest("a".repeat(101), MuscleGroup.CHEST, "a".repeat(1001));

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    // Error Handling Tests
    @Test
    @WithMockUser(roles = "USER")
    public void testGetExerciseNotFound() throws Exception {
        mockMvc.perform(get("/exercises/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateExerciseWithNonExistentId() throws Exception {
        UpdateExerciseRequest request = new UpdateExerciseRequest(999, "Pull Up", MuscleGroup.BACK, "A basic pull up exercise");

        mockMvc.perform(put("/exercises/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteExerciseWithNonExistentId() throws Exception {
        mockMvc.perform(delete("/exercises/999"))
                .andExpect(status().isNotFound());
    }

    // Security Tests
    @Test
    public void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateExerciseRequest("Push Up", MuscleGroup.CHEST, "A basic push up exercise"))))
                .andExpect(status().isUnauthorized());
    }

    // Edge Cases
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateExerciseWithInvalidDataType() throws Exception {
        String invalidRequest = "{\"name\": \"Push Up\", \"muscleGroup\": \"INVALID\", \"description\": \"A basic push up exercise\"}";

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateDuplicateExercise() throws Exception {
        CreateExerciseRequest request = new CreateExerciseRequest("Push Up", MuscleGroup.CHEST, "A basic push up exercise");

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testConcurrentUpdateExercise() throws Exception {
        ExerciseEntity exercise = ExerciseEntity.builder()
                .name("Push Up")
                .muscleGroup(MuscleGroup.CHEST)
                .description("A basic push up exercise")
                .createdAt(LocalDateTime.now())
                .build();
        exercise = exerciseRepository.save(exercise);

        UpdateExerciseRequest request1 = new UpdateExerciseRequest(exercise.getId(), "Pull Up", MuscleGroup.BACK, "A basic pull up exercise");
        UpdateExerciseRequest request2 = new UpdateExerciseRequest(exercise.getId(), "Chin Up", MuscleGroup.BACK, "A basic chin up exercise");

        mockMvc.perform(put("/exercises/" + exercise.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request1)))
                .andExpect(status().isOk());

        mockMvc.perform(put("/exercises/" + exercise.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request2)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateExerciseResponseContent() throws Exception {
        CreateExerciseRequest request = new CreateExerciseRequest("Push Up", MuscleGroup.CHEST, "A basic push up exercise");

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Push Up"))
                .andExpect(jsonPath("$.muscleGroup").value("CHEST"))
                .andExpect(jsonPath("$.description").value("A basic push up exercise"));
    }
}