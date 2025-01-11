package com.fontys.fitquest.business;

import com.fontys.fitquest.business.exception.NameAlreadyExistsException;
import com.fontys.fitquest.business.implementation.CreateUserUseCaseImpl;
import com.fontys.fitquest.domain.requests.CreateUserRequest;
import com.fontys.fitquest.domain.responses.CreateUserResponse;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCaseImpl;

    @Test
    void createUser_shouldCreateNewUser() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("john@example.com", "password123", Role.USER, "john");
        UserEntity savedUser = new UserEntity(1L, "john@example.com", "password123", Role.USER, "john");
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword123");

        // Act
        CreateUserResponse response = createUserUseCaseImpl.createUser(request);

        // Assert
        assertNotNull(response);
        assertEquals("john@example.com", response.getEmail());
        assertEquals("USER", response.getRole());
    }

    @Test
    void createUser_shouldThrowExceptionIfEmailExists() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("john@example.com", "password123", Role.USER, "john");
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(NameAlreadyExistsException.class, () -> createUserUseCaseImpl.createUser(request));
    }
}