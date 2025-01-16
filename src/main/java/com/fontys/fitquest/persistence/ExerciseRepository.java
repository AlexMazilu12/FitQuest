package com.fontys.fitquest.persistence;

import com.fontys.fitquest.domain.MuscleGroup;
import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {
    boolean existsByName(String name);

    @Query("SELECT e FROM ExerciseEntity e " +
            "WHERE (:muscleGroup IS NULL OR e.muscleGroup = :muscleGroup) " +
            "ORDER BY " +
            "CASE WHEN :orderBy = 'name' AND :direction = 'asc' THEN e.name END ASC, " +
            "CASE WHEN :orderBy = 'name' AND :direction = 'desc' THEN e.name END DESC, " +
            "CASE WHEN :orderBy = 'createdAt' AND :direction = 'asc' THEN e.createdAt END ASC, " +
            "CASE WHEN :orderBy = 'createdAt' AND :direction = 'desc' THEN e.createdAt END DESC")
    List<ExerciseEntity> findByFilters(
            @Param("muscleGroup") MuscleGroup muscleGroup,
            @Param("orderBy") String orderBy,
            @Param("direction") String direction);
}