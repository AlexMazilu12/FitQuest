package com.fontys.fitquest.domain.responses;

import com.fontys.fitquest.domain.WorkoutExercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetExercisesForWorkoutResponse {
    private List<WorkoutExercise> exercises;
}