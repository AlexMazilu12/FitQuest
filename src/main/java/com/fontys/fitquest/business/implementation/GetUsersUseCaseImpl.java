package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetUsersUseCase;
import com.fontys.fitquest.domain.requests.GetAllUsersRequest;
import com.fontys.fitquest.domain.responses.GetAllUsersResponse;
import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private UserRepository userRepository;

    @Override
        public GetAllUsersResponse getUsers(final GetAllUsersRequest request){
        List<UserEntity> results;
            results = userRepository.findAll();

        final GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> users = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setUsers(users);

        return response;
    }
}
