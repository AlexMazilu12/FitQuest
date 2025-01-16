package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.GetUserStatisticsUseCaseImpl;
import com.fontys.fitquest.domain.responses.UserStatisticsResponse;
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

public class GetUserStatisticsUseCaseImplTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks
    private GetUserStatisticsUseCaseImpl getUserStatisticsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserStatistics() {
        // Arrange
        UserStatisticsResponse user1 = new UserStatisticsResponse(1L, "User 1", 5.0);
        UserStatisticsResponse user2 = new UserStatisticsResponse(2L, "User 2", 7.0);
        List<UserStatisticsResponse> userStatistics = Arrays.asList(user1, user2);

        when(statisticsRepository.findUserStatistics()).thenReturn(userStatistics);

        // Act
        List<UserStatisticsResponse> response = getUserStatisticsUseCase.getUserStatistics();

        // Assert
        assertEquals(2, response.size());
        assertEquals(user1, response.get(0));
        assertEquals(user2, response.get(1));
    }
}