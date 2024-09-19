package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.DeleteUserUseCase;
import com.fontys.fitquest.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Override
    public void deleteUser(long studentId) {
        this.userRepository.deleteById(studentId);
    }
}
