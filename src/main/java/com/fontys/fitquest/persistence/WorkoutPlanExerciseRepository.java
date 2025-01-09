package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutPlanExerciseRepository extends JpaRepository<WorkoutPlanExerciseEntity, Long> {
    List<WorkoutPlanExerciseEntity> findByWorkoutPlanId(long workoutPlanId);
}