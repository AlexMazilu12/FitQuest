package com.fontys.fitquest;

import com.fontys.fitquest.business.exception.InvalidCredentialsException;
import com.fontys.fitquest.business.exception.UserAlreadyExistsException;
import com.fontys.fitquest.business.implementation.AuthenticationService;
import com.fontys.fitquest.configuration.token.AccessTokenEncoder;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.domain.requests.LoginRequest;
import com.fontys.fitquest.domain.responses.LoginResponse;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_validCredentials_returnsAccessToken() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("john@example.com", "password123", Role.USER, "John Doe");
        UserEntity userEntity = new UserEntity(1L, "john@example.com", "encodedPassword", Role.USER, "John Doe");
        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())).thenReturn(true);
        when(accessTokenEncoder.encode(any())).thenReturn("accessToken");

        // Act
        LoginResponse response = authenticationService.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
    }

    @Test
    void login_invalidCredentials_throwsException() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("john@example.com", "wrongPassword", Role.USER, "John Doe");
        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(new UserEntity()));
        when(passwordEncoder.matches(loginRequest.getPassword(), "encodedPassword")).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> authenticationService.login(loginRequest));
    }

    @Test
    void createUser_userAlreadyExists_throwsException() {
        // Arrange
        LoginRequest request = new LoginRequest("john@example.com", "password123", Role.USER, "John Doe");
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> authenticationService.createUser(request));
    }

    @Test
    void createUser_validRequest_savesUser() {
        // Arrange
        LoginRequest request = new LoginRequest("john@example.com", "password123", Role.USER, "John Doe");
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        // Act
        authenticationService.createUser(request);

        // Assert
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}