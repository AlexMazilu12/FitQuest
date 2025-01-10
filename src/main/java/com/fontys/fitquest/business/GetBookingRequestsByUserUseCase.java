package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.responses.GetBookingRequestsByUserResponse;

import java.util.List;

public interface GetBookingRequestsByUserUseCase {
    List<GetBookingRequestsByUserResponse> getBookingRequestsByUserOrTrainer(Long userId, Long trainerId);
}