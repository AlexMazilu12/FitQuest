package com.fontys.fitquest.domain.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateBookingRequest {
    private Long userId;

    @NotBlank
    private String userName;
    private Long trainerId;

    @NotBlank
    private String message;
    private LocalDateTime createdAt;
}