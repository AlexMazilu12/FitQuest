package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.GetUserUseCaseImpl;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class GetUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser_validId_returnsUser() {
        // Arrange
        long userId = 1L;
        UserEntity userEntity = new UserEntity(userId, "john@example.com", "password123", Role.USER, "John Doe");
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        Optional<User> result = getUserUseCase.getUser(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("john@example.com", result.get().getEmail());
    }
}