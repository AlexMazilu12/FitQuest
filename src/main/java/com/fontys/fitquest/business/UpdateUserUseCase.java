package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.UpdateUserRequest;


public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
