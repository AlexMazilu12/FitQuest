package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.GetWorkoutsPerMonthUseCaseImpl;
import com.fontys.fitquest.domain.responses.WorkoutsPerMonthResponse;
import com.fontys.fitquest.persistence.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetWorkoutsPerMonthUseCaseImplTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks
    private GetWorkoutsPerMonthUseCaseImpl getWorkoutsPerMonthUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWorkoutsPerMonth() {
        // Arrange
        WorkoutsPerMonthResponse month1 = new WorkoutsPerMonthResponse(1, 10L);
        WorkoutsPerMonthResponse month2 = new WorkoutsPerMonthResponse(2, 15L);
        List<WorkoutsPerMonthResponse> workoutsPerMonth = Arrays.asList(month1, month2);

        when(statisticsRepository.findWorkoutsPerMonth()).thenReturn(workoutsPerMonth);

        // Act
        List<WorkoutsPerMonthResponse> response = getWorkoutsPerMonthUseCase.getWorkoutsPerMonth();

        // Assert
        assertEquals(2, response.size());
        assertEquals(month1, response.get(0));
        assertEquals(month2, response.get(1));
    }
}