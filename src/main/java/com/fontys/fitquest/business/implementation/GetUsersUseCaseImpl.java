package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetUsersUseCase;
import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.domain.requests.GetAllUsersRequest;
import com.fontys.fitquest.domain.responses.GetAllUsersResponse;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository userRepository;

    @Override
    public GetAllUsersResponse getUsers(final GetAllUsersRequest request) {
        List<UserEntity> results;
        if (request.getRole() != null) {
            results = userRepository.findByRole(request.getRole());
        } else {
            results = userRepository.findAll();
        }

        List<User> users = results.stream()
                .map(UserConverter::convert)
                .collect(Collectors.toList());

        GetAllUsersResponse response = new GetAllUsersResponse();
        response.setUsers(users);

        return response;
    }
}