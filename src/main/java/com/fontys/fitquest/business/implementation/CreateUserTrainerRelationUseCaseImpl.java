package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.CreateUserTrainerRelationUseCase;
import com.fontys.fitquest.domain.requests.CreateUserTrainerRelationRequest;
import com.fontys.fitquest.domain.responses.CreateUserTrainerRelationResponse;
import com.fontys.fitquest.persistence.TrainerUserAssignmentRepository;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.TrainerUserAssignmentEntity;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserTrainerRelationUseCaseImpl implements CreateUserTrainerRelationUseCase {

    @Autowired
    private TrainerUserAssignmentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateUserTrainerRelationResponse createUserTrainerRelation(CreateUserTrainerRelationRequest request) {
        UserEntity trainer = userRepository.findById(request.getTrainerId()).orElseThrow(() -> new RuntimeException("Trainer not found"));
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        TrainerUserAssignmentEntity entity = new TrainerUserAssignmentEntity();
        entity.setTrainer(trainer);
        entity.setUser(user);
        entity.setPrice(request.getPrice());
        entity = repository.save(entity);
        return new CreateUserTrainerRelationResponse(entity.getId());
    }
}