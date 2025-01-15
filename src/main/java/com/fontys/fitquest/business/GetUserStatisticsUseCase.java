package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.responses.UserStatisticsResponse;

import java.util.List;

public interface GetUserStatisticsUseCase {
    List<UserStatisticsResponse> getUserStatistics();
}