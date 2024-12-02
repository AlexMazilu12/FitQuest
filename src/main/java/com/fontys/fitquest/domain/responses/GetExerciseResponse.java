package com.fontys.fitquest.domain.responses;

import com.fontys.fitquest.domain.Exercise;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetExerciseResponse {
    private Exercise exercise;
}