package com.fontys.fitquest.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateBookingResponse {
    private Long id;
    private Long userId;
    private String userName;
    private Long trainerId;
    private String message;
    private LocalDateTime createdAt;
}