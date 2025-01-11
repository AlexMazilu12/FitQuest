package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.responses.GetUsersForTrainerResponse;
import java.util.List;

public interface GetUsersForTrainerUseCase {
    List<GetUsersForTrainerResponse> getUsersForTrainer(Long trainerId);
}