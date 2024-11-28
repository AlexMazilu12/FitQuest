package com.fontys.fitquest.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidWorkoutPlan extends ResponseStatusException {
    public InvalidWorkoutPlan(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
