package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetUsersForTrainerUseCase;
import com.fontys.fitquest.domain.responses.GetUsersForTrainerResponse;
import com.fontys.fitquest.persistence.TrainerUserAssignmentRepository;
import com.fontys.fitquest.persistence.entity.TrainerUserAssignmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetUsersForTrainerUseCaseImpl implements GetUsersForTrainerUseCase {

    @Autowired
    private TrainerUserAssignmentRepository repository;

    @Override
    public List<GetUsersForTrainerResponse> getUsersForTrainer(Long trainerId) {
        List<TrainerUserAssignmentEntity> assignments = repository.findByTrainer_Id(trainerId);
        return assignments.stream()
                .map(assignment -> new GetUsersForTrainerResponse(assignment.getUser().getId(), assignment.getUser().getName(), assignment.getUser().getEmail()))
                .collect(Collectors.toList());
    }
}