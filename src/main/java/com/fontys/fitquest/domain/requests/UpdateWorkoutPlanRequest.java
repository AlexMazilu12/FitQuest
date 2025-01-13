package com.fontys.fitquest.domain.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkoutPlanRequest {
    private Long id;

    @NotNull
    private int userId;

    @NotBlank
    private String title;

    private String description;
}
