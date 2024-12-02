package com.fontys.fitquest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private int id;
    private String name;
    private MuscleGroup muscleGroup;
    private String description;
    private LocalDateTime createdAt;
}