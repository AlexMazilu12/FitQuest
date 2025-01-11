package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.DeleteUserTrainerRelationUseCase;
import com.fontys.fitquest.persistence.TrainerUserAssignmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserTrainerRelationUseCaseImpl implements DeleteUserTrainerRelationUseCase {

    private final TrainerUserAssignmentRepository repository;

    @Override
    public void deleteUserTrainerRelation(Long id) {
        repository.deleteById(id);
    }
}