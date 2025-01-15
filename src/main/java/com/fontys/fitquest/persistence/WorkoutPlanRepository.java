package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity, Long> {
    boolean existsByUserIdAndTitle(int userId, String title);
    Optional<WorkoutPlanEntity> findById(Long id);

    @Query("SELECT u.id, u.email, COUNT(wp) FROM WorkoutPlanEntity wp JOIN UserEntity u ON wp.userId = u.id GROUP BY u.id, u.email")
    List<Object[]> countWorkoutPlansByUserWithDetails();
}
