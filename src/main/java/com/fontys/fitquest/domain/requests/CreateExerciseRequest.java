package com.fontys.fitquest.domain.requests;

import com.fontys.fitquest.domain.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotNull
    private MuscleGroup muscleGroup;
    private String description;
}