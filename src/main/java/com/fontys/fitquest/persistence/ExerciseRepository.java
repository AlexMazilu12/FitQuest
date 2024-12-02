package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {
}