package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.DeleteUserTrainerRelationUseCase;
import com.fontys.fitquest.persistence.TrainerUserAssignmentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteUserTrainerRelationUseCaseImpl implements DeleteUserTrainerRelationUseCase {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserTrainerRelationUseCaseImpl.class);

    private final TrainerUserAssignmentRepository repository;

    @Override
    @Transactional
    public void deleteUserTrainerRelation(Long trainerId, Long userId) {
        logger.info("Attempting to delete user-trainer relation with trainer_id: {} and user_id: {}", trainerId, userId);
        repository.deleteByTrainer_IdAndUser_Id(trainerId, userId);
    }
}