package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.CreateUserTrainerRelationRequest;
import com.fontys.fitquest.domain.responses.CreateUserTrainerRelationResponse;

public interface CreateUserTrainerRelationUseCase {
    CreateUserTrainerRelationResponse createUserTrainerRelation(CreateUserTrainerRelationRequest request);
}