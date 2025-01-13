package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.GetBookingRequestsByUserUseCaseImpl;
import com.fontys.fitquest.domain.responses.GetBookingRequestsByUserResponse;
import com.fontys.fitquest.persistence.BookingRequestRepository;
import com.fontys.fitquest.persistence.entity.BookingRequestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetBookingRequestsByUserUseCaseImplTest {

    @Mock
    private BookingRequestRepository bookingRequestRepository;

    @InjectMocks
    private GetBookingRequestsByUserUseCaseImpl getBookingRequestsByUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookingRequestsByUserOrTrainer() {
        BookingRequestEntity bookingRequest1 = BookingRequestEntity.builder()
                .id(1L)
                .userId(1L)
                .userName("User1")
                .trainerId(2L)
                .message("Request 1")
                .createdAt(LocalDateTime.now())
                .build();

        BookingRequestEntity bookingRequest2 = BookingRequestEntity.builder()
                .id(2L)
                .userId(1L)
                .userName("User1")
                .trainerId(3L)
                .message("Request 2")
                .createdAt(LocalDateTime.now())
                .build();

        List<BookingRequestEntity> bookingRequests = Arrays.asList(bookingRequest1, bookingRequest2);

        when(bookingRequestRepository.findByUserIdOrTrainerId(1L, 2L)).thenReturn(bookingRequests);

        List<GetBookingRequestsByUserResponse> responses = getBookingRequestsByUserUseCase.getBookingRequestsByUserOrTrainer(1L, 2L);

        assertEquals(2, responses.size());
        assertEquals(bookingRequest1.getId(), responses.get(0).getId());
        assertEquals(bookingRequest1.getUserId(), responses.get(0).getUserId());
        assertEquals(bookingRequest1.getUserName(), responses.get(0).getUserName());
        assertEquals(bookingRequest1.getTrainerId(), responses.get(0).getTrainerId());
        assertEquals(bookingRequest1.getMessage(), responses.get(0).getMessage());
        assertEquals(bookingRequest1.getCreatedAt(), responses.get(0).getCreatedAt());

        assertEquals(bookingRequest2.getId(), responses.get(1).getId());
        assertEquals(bookingRequest2.getUserId(), responses.get(1).getUserId());
        assertEquals(bookingRequest2.getUserName(), responses.get(1).getUserName());
        assertEquals(bookingRequest2.getTrainerId(), responses.get(1).getTrainerId());
        assertEquals(bookingRequest2.getMessage(), responses.get(1).getMessage());
        assertEquals(bookingRequest2.getCreatedAt(), responses.get(1).getCreatedAt());
    }
}