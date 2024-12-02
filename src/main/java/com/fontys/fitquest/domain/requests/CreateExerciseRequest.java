package com.fontys.fitquest.domain.requests;

import com.fontys.fitquest.domain.MuscleGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseRequest {
    @NotNull
    private String name;

    @NotNull
    private MuscleGroup muscleGroup;

    private String description;
}