package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.CreateUserRequest;
import com.fontys.fitquest.domain.responses.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
