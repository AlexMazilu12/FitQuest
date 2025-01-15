package com.fontys.fitquest.persistence;

import com.fontys.fitquest.domain.responses.UserStatisticsResponse;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT new com.fontys.fitquest.domain.responses.UserStatisticsResponse(u.id, u.name, AVG(wpe.exerciseCount)) " +
            "FROM UserEntity u " +
            "JOIN WorkoutPlanEntity wp ON u.id = wp.userId " +
            "JOIN (SELECT wpe.workoutPlan.id AS workoutPlanId, COUNT(*) AS exerciseCount " +
            "FROM WorkoutPlanExerciseEntity wpe " +
            "GROUP BY wpe.workoutPlan.id) wpe ON wp.id = wpe.workoutPlanId " +
            "GROUP BY u.id, u.name")
    List<UserStatisticsResponse> findUserStatistics();
}