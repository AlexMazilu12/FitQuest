package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.WorkoutPlanExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutPlanExerciseRepository extends JpaRepository<WorkoutPlanExerciseEntity, Long> {

    @Query("SELECT wpe FROM WorkoutPlanExerciseEntity wpe WHERE wpe.workoutPlan.id = :workoutPlanId")
    List<WorkoutPlanExerciseEntity> findByWorkoutPlanId(@Param("workoutPlanId") Long workoutPlanId);
}