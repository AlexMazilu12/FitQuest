package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.GetAllUsersRequest;
import com.fontys.fitquest.domain.responses.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
