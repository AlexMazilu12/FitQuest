// AuthenticationService.java
package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.exception.InvalidCredentialsException;
import com.fontys.fitquest.business.exception.UserAlreadyExistsException;
import com.fontys.fitquest.configuration.token.AccessTokenEncoder;
import com.fontys.fitquest.configuration.token.impl.AccessTokenImpl;
import com.fontys.fitquest.domain.Role;
import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.domain.requests.LoginRequest;
import com.fontys.fitquest.domain.responses.LoginResponse;
import com.fontys.fitquest.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail()).map(UserConverter::convert);
        if (user.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.get().getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    public void createUser(LoginRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        User tempUser = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();

        saveNewUser(tempUser);
    }

    private User saveNewUser(User request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .name(request.getName())
                .build();

        return userRepository.save(newUser);
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(Optional<User> user) {
        Long userId = user.get().getId();
        Role role = user.get().getRole();

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.get().getEmail(), userId, role, true));
    }
}
