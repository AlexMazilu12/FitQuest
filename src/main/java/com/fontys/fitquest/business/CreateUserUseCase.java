package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.CreateUserRequest;
import com.fontys.fitquest.domain.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
