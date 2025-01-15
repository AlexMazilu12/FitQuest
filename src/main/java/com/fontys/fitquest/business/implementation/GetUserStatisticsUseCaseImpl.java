package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetUserStatisticsUseCase;
import com.fontys.fitquest.domain.responses.UserStatisticsResponse;
import com.fontys.fitquest.persistence.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserStatisticsUseCaseImpl implements GetUserStatisticsUseCase {

    private final StatisticsRepository statisticsRepository;

    @Override
    public List<UserStatisticsResponse> getUserStatistics() {
        return statisticsRepository.findUserStatistics();
    }
}