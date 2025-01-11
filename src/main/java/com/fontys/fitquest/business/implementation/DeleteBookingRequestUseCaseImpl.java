package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.DeleteBookingRequestUseCase;
import com.fontys.fitquest.persistence.BookingRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteBookingRequestUseCaseImpl implements DeleteBookingRequestUseCase {

    private final BookingRequestRepository repository;

    @Override
    public void deleteBookingRequest(Long id) {
        repository.deleteById(id);
    }
}