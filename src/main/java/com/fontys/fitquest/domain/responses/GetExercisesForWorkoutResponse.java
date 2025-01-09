package com.fontys.fitquest.domain.responses;

import com.fontys.fitquest.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetExercisesForWorkoutResponse {
    private List<Exercise> exercises;
}