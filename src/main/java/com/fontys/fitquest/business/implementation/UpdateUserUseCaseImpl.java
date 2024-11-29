package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.UpdateUserUseCase;
import com.fontys.fitquest.business.exception.InvalidUserException;
import com.fontys.fitquest.domain.requests.UpdateUserRequest;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private UserRepository userRepository;

    @Override
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getId());
        if (userOptional.isEmpty()) {
            throw new InvalidUserException("USER_ID_INVALID");
        }

        UserEntity user = userOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user) {
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        userRepository.save(user);
    }
}
