package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.GetUsersForTrainerUseCaseImpl;
import com.fontys.fitquest.domain.responses.GetUsersForTrainerResponse;
import com.fontys.fitquest.persistence.TrainerUserAssignmentRepository;
import com.fontys.fitquest.persistence.entity.TrainerUserAssignmentEntity;
import com.fontys.fitquest.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetUsersForTrainerUseCaseImplTest {

    @Mock
    private TrainerUserAssignmentRepository repository;

    @InjectMocks
    private GetUsersForTrainerUseCaseImpl getUsersForTrainerUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsersForTrainer() {
        Long trainerId = 1L;

        UserEntity user1 = UserEntity.builder()
                .id(2L)
                .name("User1")
                .email("user1@example.com")
                .build();

        UserEntity user2 = UserEntity.builder()
                .id(3L)
                .name("User2")
                .email("user2@example.com")
                .build();

        TrainerUserAssignmentEntity assignment1 = TrainerUserAssignmentEntity.builder()
                .trainer(UserEntity.builder().id(trainerId).build())
                .user(user1)
                .build();

        TrainerUserAssignmentEntity assignment2 = TrainerUserAssignmentEntity.builder()
                .trainer(UserEntity.builder().id(trainerId).build())
                .user(user2)
                .build();

        when(repository.findByTrainer_Id(trainerId)).thenReturn(Arrays.asList(assignment1, assignment2));

        List<GetUsersForTrainerResponse> responses = getUsersForTrainerUseCase.getUsersForTrainer(trainerId);

        assertEquals(2, responses.size());
        assertEquals(user1.getId(), responses.get(0).getId());
        assertEquals(user1.getName(), responses.get(0).getName());
        assertEquals(user1.getEmail(), responses.get(0).getEmail());
        assertEquals(user2.getId(), responses.get(1).getId());
        assertEquals(user2.getName(), responses.get(1).getName());
        assertEquals(user2.getEmail(), responses.get(1).getEmail());
    }
}