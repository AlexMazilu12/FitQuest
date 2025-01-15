package com.fontys.fitquest.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStatisticsResponse {
    private Long userId;
    private String userName;
    private Double averageExercisesPerWorkout;
}