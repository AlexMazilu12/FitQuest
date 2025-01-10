package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.requests.CreateBookingRequest;
import com.fontys.fitquest.domain.responses.CreateBookingResponse;

public interface CreateBookingRequestUseCase {
    CreateBookingResponse createBookingRequest(CreateBookingRequest request);
}