package com.fontys.fitquest.domain.requests;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateBookingRequest {
    private Long userId;
    private String userName;
    private Long trainerId;
    private String message;
    private LocalDateTime createdAt;
}