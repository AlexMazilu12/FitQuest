package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.GetAllUsersRequest;
import com.fontys.fitquest.domain.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
