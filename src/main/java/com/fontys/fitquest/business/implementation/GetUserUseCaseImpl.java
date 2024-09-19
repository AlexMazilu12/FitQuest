package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetUserUseCase;
import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(long userId){
        return userRepository.findById(userId).map(UserConverter::convert);
    }
}
