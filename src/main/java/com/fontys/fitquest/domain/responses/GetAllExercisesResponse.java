package com.fontys.fitquest.domain.responses;

import com.fontys.fitquest.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllExercisesResponse {
    private List<Exercise> exercises;
}