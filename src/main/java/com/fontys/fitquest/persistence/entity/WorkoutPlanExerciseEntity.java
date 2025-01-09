package com.fontys.fitquest.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workout_plan_exercises")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlanExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id", nullable = false)
    private WorkoutPlanEntity workoutPlan;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    private int sets;
    private int reps;
    private Integer restTime;

    public Long getExerciseId() {
        return (long) exercise.getId();
    }
}
