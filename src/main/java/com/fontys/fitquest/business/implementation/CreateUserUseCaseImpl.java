package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.CreateUserUseCase;
import com.fontys.fitquest.business.exception.NameAlreadyExistsException;
import com.fontys.fitquest.domain.CreateUserRequest;
import com.fontys.fitquest.domain.CreateUserResponse;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByName(request.getName())) {
            throw new NameAlreadyExistsException();
        }

        UserEntity savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        System.out.println("Saving user: " + newUser);
        UserEntity savedUser = userRepository.save(newUser);

        System.out.println("Saved user: " + savedUser);
        return savedUser;
    }
}
