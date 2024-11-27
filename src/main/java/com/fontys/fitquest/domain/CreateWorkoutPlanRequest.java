package com.fontys.fitquest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutPlanRequest {
    private int userId;

    @NotNull
    private String title;

    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
