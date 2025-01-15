package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity, Long> {
    boolean existsByUserIdAndTitle(int userId, String title);
    Optional<WorkoutPlanEntity> findById(Long id);

    List<WorkoutPlanEntity> findByUserId(int userId);
}
