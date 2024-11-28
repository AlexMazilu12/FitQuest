package com.fontys.fitquest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkoutPlanRequest {
    private Long id;
    private int userId;

    @NotNull
    private String title;

    private String description;
}
