package com.fontys.fitquest;

import com.fontys.fitquest.business.exception.NameAlreadyExistsException;
import com.fontys.fitquest.business.implementation.CreateUserUseCaseImpl;
import com.fontys.fitquest.domain.CreateUserRequest;
import com.fontys.fitquest.domain.CreateUserResponse;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCaseImpl;

    @Test
    void createUser_shouldCreateNewUser() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("john", "john@example.com", "password123", Role.USER);
        UserEntity savedUser = new UserEntity(1L, "john", "john@example.com", "password123", Role.USER);
        when(userRepository.existsByName(request.getName())).thenReturn(false);
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        // Act
        CreateUserResponse response = createUserUseCaseImpl.createUser(request);

        // Assert
        assertNotNull(response);
        assertEquals("john", response.getName());
        assertEquals("john@example.com", response.getEmail());
        assertEquals("USER", response.getRole());
    }

    @Test
    void createUser_shouldThrowExceptionIfNameExists() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("john", "john@example.com", "password123", Role.USER);
        when(userRepository.existsByName(request.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(NameAlreadyExistsException.class, () -> createUserUseCaseImpl.createUser(request));
    }
}
