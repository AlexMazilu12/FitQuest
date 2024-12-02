package com.fontys.fitquest.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateExerciseResponse {
    private int id;
    private String name;
    private String muscleGroup;
    private String description;
}