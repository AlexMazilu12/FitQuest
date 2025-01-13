package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.CreateUserTrainerRelationUseCaseImpl;
import com.fontys.fitquest.domain.requests.CreateUserTrainerRelationRequest;
import com.fontys.fitquest.domain.responses.CreateUserTrainerRelationResponse;
import com.fontys.fitquest.persistence.TrainerUserAssignmentRepository;
import com.fontys.fitquest.persistence.UserRepository;
import com.fontys.fitquest.persistence.entity.TrainerUserAssignmentEntity;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CreateUserTrainerRelationUseCaseImplTest {

    @Mock
    private TrainerUserAssignmentRepository repository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserTrainerRelationUseCaseImpl createUserTrainerRelationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserTrainerRelation() {
        CreateUserTrainerRelationRequest request = CreateUserTrainerRelationRequest.builder()
                .trainerId(1L)
                .userId(2L)
                .price(new BigDecimal("100.0"))
                .build();

        UserEntity trainer = UserEntity.builder()
                .id(1L)
                .name("Trainer")
                .build();

        UserEntity user = UserEntity.builder()
                .id(2L)
                .name("User")
                .build();

        TrainerUserAssignmentEntity entityToSave = TrainerUserAssignmentEntity.builder()
                .trainer(trainer)
                .user(user)
                .price(new BigDecimal("100.0"))
                .build();

        TrainerUserAssignmentEntity savedEntity = TrainerUserAssignmentEntity.builder()
                .id(1L)
                .trainer(trainer)
                .user(user)
                .price(new BigDecimal("100.0"))
                .build();

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(trainer));
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.of(user));
        when(repository.save(entityToSave)).thenReturn(savedEntity);

        CreateUserTrainerRelationResponse response = createUserTrainerRelationUseCase.createUserTrainerRelation(request);

        assertEquals(savedEntity.getId(), response.getId());
    }
}