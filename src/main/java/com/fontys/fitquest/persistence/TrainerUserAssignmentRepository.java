package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.TrainerUserAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerUserAssignmentRepository extends JpaRepository<TrainerUserAssignmentEntity, Long> {
}