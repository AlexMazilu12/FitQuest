package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.CreateBookingRequestUseCase;
import com.fontys.fitquest.business.DeleteBookingRequestUseCase;
import com.fontys.fitquest.business.GetBookingRequestsByUserUseCase;
import com.fontys.fitquest.domain.requests.CreateBookingRequest;
import com.fontys.fitquest.domain.responses.GetBookingRequestsByUserResponse;
import com.fontys.fitquest.domain.responses.CreateBookingResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-requests")
@AllArgsConstructor
public class BookingRequestByUserController {

    private final CreateBookingRequestUseCase createBookingRequestUseCase;
    private final GetBookingRequestsByUserUseCase getBookingRequestsByUserUseCase;
    private final DeleteBookingRequestUseCase deleteBookingRequestUseCase;

    @PostMapping
    public ResponseEntity<CreateBookingResponse> createBookingRequest(@RequestBody CreateBookingRequest request) {
        CreateBookingResponse response = createBookingRequestUseCase.createBookingRequest(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user-or-trainer")
    public ResponseEntity<List<GetBookingRequestsByUserResponse>> getBookingRequestsByUserOrTrainer(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long trainerId) {
        List<GetBookingRequestsByUserResponse> responses = getBookingRequestsByUserUseCase.getBookingRequestsByUserOrTrainer(userId, trainerId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingRequest(@PathVariable Long id) {
        deleteBookingRequestUseCase.deleteBookingRequest(id);
        return ResponseEntity.noContent().build();
    }
}