package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetBookingRequestsByUserUseCase;
import com.fontys.fitquest.domain.responses.GetBookingRequestsByUserResponse;
import com.fontys.fitquest.persistence.BookingRequestRepository;
import com.fontys.fitquest.persistence.entity.BookingRequestEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetBookingRequestsByUserUseCaseImpl implements GetBookingRequestsByUserUseCase {

    private final BookingRequestRepository bookingRequestRepository;

    @Override
    public List<GetBookingRequestsByUserResponse> getBookingRequestsByUserOrTrainer(Long userId, Long trainerId) {
        List<BookingRequestEntity> bookingRequests = bookingRequestRepository.findByUserIdOrTrainerId(userId, trainerId);
        return bookingRequests.stream()
                .map(entity -> new GetBookingRequestsByUserResponse(
                        entity.getId(),
                        entity.getUserId(),
                        entity.getUserName(),
                        entity.getTrainerId(),
                        entity.getMessage(),
                        entity.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}