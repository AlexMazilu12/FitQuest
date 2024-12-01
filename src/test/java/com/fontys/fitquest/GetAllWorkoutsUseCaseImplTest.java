package com.fontys.fitquest;

import com.fontys.fitquest.business.implementation.GetAllWorkoutsUseCaseImpl;
import com.fontys.fitquest.domain.requests.GetAllWorkoutsRequest;
import com.fontys.fitquest.domain.responses.GetAllWorkoutsResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GetAllWorkoutsUseCaseImplTest {

    @Mock
    private WorkoutPlanRepository workoutPlanRepository;

    @InjectMocks
    private GetAllWorkoutsUseCaseImpl getAllWorkoutsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllWorkouts_returnsWorkoutList() {
        // Arrange
        WorkoutPlanEntity workoutPlanEntity = new WorkoutPlanEntity();
        workoutPlanEntity.setId(1L);
        when(workoutPlanRepository.findAll()).thenReturn(Stream.of(workoutPlanEntity).collect(Collectors.toList()));

        // Act
        GetAllWorkoutsResponse response = getAllWorkoutsUseCase.getAllWorkouts(new GetAllWorkoutsRequest());

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getWorkoutPlans().size());
        assertEquals(1L, response.getWorkoutPlans().get(0).getId());
    }
}