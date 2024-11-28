package com.fontys.fitquest.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteWorkoutPlanResponse {
    private Long id;
    private boolean success;
}