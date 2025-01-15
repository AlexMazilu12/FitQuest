package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetWorkoutsPerMonthUseCase;
import com.fontys.fitquest.domain.responses.WorkoutsPerMonthResponse;
import com.fontys.fitquest.persistence.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetWorkoutsPerMonthUseCaseImpl implements GetWorkoutsPerMonthUseCase {
    private final StatisticsRepository statisticsRepository;

    @Override
    public List<WorkoutsPerMonthResponse> getWorkoutsPerMonth() {
        return statisticsRepository.findWorkoutsPerMonth();
    }
}