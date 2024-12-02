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
import com.fontys.fitquest.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService extends DefaultOAuth2UserService{
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

        Role role = request.getRole() != null ? request.getRole() : Role.USER;

        String name = request.getName() != null ? request.getName() : "Guest";

        User tempUser = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(role)
                .name(name)
                .build();

        saveNewUser(tempUser);
    }

    private UserEntity saveNewUser(User request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(request.getRole())
                .name(request.getName())
                .build();

        return userRepository.save(newUser);
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(Optional<User> user) {
        if (user.isPresent()) {
            Long userId = user.get().getId();
            Role role = user.get().getRole();
            return accessTokenEncoder.encode(
                    new AccessTokenImpl(user.get().getEmail(), userId, role));
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
