package com.fontys.fitquest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.domain.requests.CreateUserRequest;
import com.fontys.fitquest.domain.requests.UpdateUserRequest;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsersControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    // CRUD Operations //
    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateUser() throws Exception {
        CreateUserRequest request = new CreateUserRequest("test@example.com", "password", Role.USER, "Test User");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.role").value("USER"))
                .andExpect(jsonPath("$.name").value("Test User"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateUser() throws Exception {
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .password("password")
                .role(Role.USER)
                .name("Test User")
                .build();
        user = userRepository.save(user);

        UpdateUserRequest request = new UpdateUserRequest(user.getId(), "updated@example.com", Role.ADMIN, "Updated User");

        mockMvc.perform(put("/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());

        Optional<UserEntity> updatedUser = userRepository.findById(user.getId());
        assert updatedUser.isPresent();
        assert updatedUser.get().getEmail().equals("updated@example.com");
        assert updatedUser.get().getRole() == Role.ADMIN;
        assert updatedUser.get().getName().equals("Updated User");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteUser() throws Exception {
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .password("password")
                .role(Role.USER)
                .name("Test User")
                .build();
        user = userRepository.save(user);

        mockMvc.perform(delete("/users/" + user.getId()))
                .andExpect(status().isNoContent());

        Optional<UserEntity> deletedUser = userRepository.findById(user.getId());
        assert deletedUser.isEmpty();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllUsers() throws Exception {
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .password("password")
                .role(Role.USER)
                .name("Test User")
                .build();
        userRepository.save(user);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[0].email").value("test@example.com"))
                .andExpect(jsonPath("$.users[0].role").value("USER"))
                .andExpect(jsonPath("$.users[0].name").value("Test User"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetUser() throws Exception {
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .password("password")
                .role(Role.USER)
                .name("Test User")
                .build();
        user = userRepository.save(user);

        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.role").value("USER"))
                .andExpect(jsonPath("$.name").value("Test User"));
    }

    // Validation Tests //
    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateUserValidation() throws Exception {
        CreateUserRequest request = new CreateUserRequest("", "password", Role.USER, "");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateUserValidation() throws Exception {
        UpdateUserRequest request = new UpdateUserRequest(1L, "", Role.USER, "");

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    // Error Handling Tests //
    @Test
    @WithMockUser(roles = "USER")
    void testGetUserNotFound() throws Exception {
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateUserWithNonExistentId() throws Exception {
        UpdateUserRequest request = new UpdateUserRequest(999L, "updated@example.com", Role.ADMIN, "Updated User");

        mockMvc.perform(put("/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteUserWithNonExistentId() throws Exception {
        mockMvc.perform(delete("/users/999"))
                .andExpect(status().isNotFound());
    }

    // Security Tests //
    @Test
    void testUnauthorizedAccess() throws Exception {
        CreateUserRequest request = new CreateUserRequest("test@example.com", "password", Role.USER, "Test User");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user1@example.com", roles = "USER")
    void testUserCannotUpdateAnotherUser() throws Exception {
        UserEntity user = UserEntity.builder()
                .email("user2@example.com")
                .password("password")
                .role(Role.USER)
                .name("User Two")
                .build();
        user = userRepository.save(user);

        UpdateUserRequest request = new UpdateUserRequest(user.getId(), "updated@example.com", Role.USER, "Updated User");

        mockMvc.perform(put("/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }
}