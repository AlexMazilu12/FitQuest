package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.CreateBookingRequestUseCaseImpl;
import com.fontys.fitquest.domain.requests.CreateBookingRequest;
import com.fontys.fitquest.domain.responses.CreateBookingResponse;
import com.fontys.fitquest.persistence.BookingRequestRepository;
import com.fontys.fitquest.persistence.entity.BookingRequestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CreateBookingRequestUseCaseImplTest {

    @Mock
    private BookingRequestRepository bookingRequestRepository;

    @InjectMocks
    private CreateBookingRequestUseCaseImpl createBookingRequestUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBookingRequest() {
        CreateBookingRequest request = CreateBookingRequest.builder()
                .userId(1L)
                .userName("User1")
                .trainerId(2L)
                .message("Request message")
                .createdAt(LocalDateTime.now())
                .build();

        BookingRequestEntity bookingRequestEntity = BookingRequestEntity.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .trainerId(request.getTrainerId())
                .message(request.getMessage())
                .createdAt(request.getCreatedAt())
                .build();

        BookingRequestEntity savedEntity = BookingRequestEntity.builder()
                .id(1L)
                .userId(request.getUserId())
                .userName(request.getUserName())
                .trainerId(request.getTrainerId())
                .message(request.getMessage())
                .createdAt(request.getCreatedAt())
                .build();

        when(bookingRequestRepository.save(bookingRequestEntity)).thenReturn(savedEntity);

        CreateBookingResponse response = createBookingRequestUseCase.createBookingRequest(request);

        assertEquals(savedEntity.getId(), response.getId());
        assertEquals(savedEntity.getUserId(), response.getUserId());
        assertEquals(savedEntity.getUserName(), response.getUserName());
        assertEquals(savedEntity.getTrainerId(), response.getTrainerId());
        assertEquals(savedEntity.getMessage(), response.getMessage());
        assertEquals(savedEntity.getCreatedAt(), response.getCreatedAt());
    }
}