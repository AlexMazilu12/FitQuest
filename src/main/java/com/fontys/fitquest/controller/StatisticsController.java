package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.GetUserStatisticsUseCase;
import com.fontys.fitquest.business.GetWorkoutsPerMonthUseCase;
import com.fontys.fitquest.domain.responses.UserStatisticsResponse;
import com.fontys.fitquest.domain.responses.WorkoutsPerMonthResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final GetUserStatisticsUseCase getUserStatisticsUseCase;
    private final GetWorkoutsPerMonthUseCase getWorkoutsPerMonthUseCase;

    @GetMapping("/user-averages")
    public List<UserStatisticsResponse> getUserStatistics() {
        return getUserStatisticsUseCase.getUserStatistics();
    }

    @GetMapping("/workouts-per-month")
    public List<WorkoutsPerMonthResponse> getWorkoutsPerMonth() {
        return getWorkoutsPerMonthUseCase.getWorkoutsPerMonth();
    }
}