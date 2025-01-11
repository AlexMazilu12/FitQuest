package com.fontys.fitquest.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExercise {
    private int id;
    private String name;
    private MuscleGroup muscleGroup;
    private String description;
    private int sets;
    private int reps;
    private Integer restTime;
}