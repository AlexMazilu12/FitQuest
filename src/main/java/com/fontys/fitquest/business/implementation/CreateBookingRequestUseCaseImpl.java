package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.CreateBookingRequestUseCase;
import com.fontys.fitquest.domain.requests.CreateBookingRequest;
import com.fontys.fitquest.domain.responses.CreateBookingResponse;
import com.fontys.fitquest.persistence.BookingRequestRepository;
import com.fontys.fitquest.persistence.entity.BookingRequestEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateBookingRequestUseCaseImpl implements CreateBookingRequestUseCase {

    private final BookingRequestRepository bookingRequestRepository;

    @Override
    public CreateBookingResponse createBookingRequest(CreateBookingRequest request) {
        BookingRequestEntity bookingRequestEntity = BookingRequestEntity.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .trainerId(request.getTrainerId())
                .message(request.getMessage())
                .createdAt(request.getCreatedAt())
                .build();

        BookingRequestEntity savedEntity = bookingRequestRepository.save(bookingRequestEntity);

        return new CreateBookingResponse(savedEntity.getId(), savedEntity.getUserId(), savedEntity.getUserName(), savedEntity.getTrainerId(), savedEntity.getMessage(), savedEntity.getCreatedAt());
    }
}