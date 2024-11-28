package com.fontys.fitquest.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidWorkoutPlanException extends ResponseStatusException {
    public InvalidWorkoutPlanException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
