package com.fontys.fitquest.persistence;

import com.fontys.fitquest.persistence.entity.TrainerUserAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainerUserAssignmentRepository extends JpaRepository<TrainerUserAssignmentEntity, Long> {

    @Query("SELECT tua FROM TrainerUserAssignmentEntity tua JOIN FETCH tua.user WHERE tua.trainer.id = :trainerId")
    List<TrainerUserAssignmentEntity> findByTrainer_Id(@Param("trainerId") Long trainerId);

    void deleteByTrainer_IdAndUser_Id(Long trainerId, Long userId);
}