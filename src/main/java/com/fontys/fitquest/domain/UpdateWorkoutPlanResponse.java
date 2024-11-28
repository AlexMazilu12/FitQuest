package com.fontys.fitquest.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UpdateWorkoutPlanResponse {
    private Long id;
    private int userId;
    private String title;
    private String description;
    private LocalDateTime updatedAt;
}
