package com.fontys.fitquest.business;

import com.fontys.fitquest.business.implementation.DeleteBookingRequestUseCaseImpl;
import com.fontys.fitquest.persistence.BookingRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DeleteBookingRequestUseCaseImplTest {

    @Mock
    private BookingRequestRepository repository;

    @InjectMocks
    private DeleteBookingRequestUseCaseImpl deleteBookingRequestUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteBookingRequest() {
        Long bookingRequestId = 1L;

        deleteBookingRequestUseCase.deleteBookingRequest(bookingRequestId);

        verify(repository).deleteById(bookingRequestId);
    }
}