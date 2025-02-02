package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.DeleteUserUseCaseImpl;
import com.fontys.fitquest.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DeleteUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteUser_validId_deletesUser() {
        // Arrange
        long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        // Act
        deleteUserUseCase.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}