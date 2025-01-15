package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.GetUserStatisticsUseCase;
import com.fontys.fitquest.domain.responses.UserStatisticsResponse;
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

    @GetMapping("/user-averages")
    public List<UserStatisticsResponse> getUserStatistics() {
        return getUserStatisticsUseCase.getUserStatistics();
    }
}