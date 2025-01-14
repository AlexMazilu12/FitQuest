package com.fontys.fitquest.business;

import com.fontys.fitquest.business.exception.UserNotFoundException;
import com.fontys.fitquest.business.implementation.UpdateUserUseCaseImpl;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.domain.requests.UpdateUserRequest;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCaseImpl;

    @Test
    void updateUser_shouldUpdateUser() {
        // Arrange
        UpdateUserRequest request = new UpdateUserRequest(1L, "john_updated@example.com", Role.TRAINER, "john_updated");
        UserEntity existingUser = new UserEntity(1L, "john@example.com", "password123", Role.USER, "john");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        updateUserUseCaseImpl.updateUser(request);

        // Assert
        assertEquals("john_updated", existingUser.getName());
        assertEquals("john_updated@example.com", existingUser.getEmail());
        assertEquals(Role.TRAINER, existingUser.getRole());
    }

    @Test
    void updateUser_shouldThrowExceptionIfUserNotFound() {
        // Arrange
        UpdateUserRequest request = new UpdateUserRequest(1L, "john_updated@example.com", Role.TRAINER, "john_updated");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> updateUserUseCaseImpl.updateUser(request));
    }
}