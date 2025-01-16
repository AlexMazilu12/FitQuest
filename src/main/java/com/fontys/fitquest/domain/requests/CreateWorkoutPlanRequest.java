package com.fontys.fitquest.domain.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutPlanRequest {
    @NotNull
    private int userId;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}