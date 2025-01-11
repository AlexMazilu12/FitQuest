package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.GetUsersUseCaseImpl;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.domain.requests.GetAllUsersRequest;
import com.fontys.fitquest.domain.responses.GetAllUsersResponse;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GetUsersUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUsersUseCaseImpl getUsersUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers_returnsUserList() {
        // Arrange
        UserEntity userEntity = new UserEntity(1L, "john@example.com", "password123", Role.USER, "John Doe");
        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        // Act
        GetAllUsersResponse response = getUsersUseCase.getUsers(new GetAllUsersRequest());

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getUsers().size());
        assertEquals("john@example.com", response.getUsers().get(0).getEmail());
    }
}