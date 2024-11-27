package com.fontys.fitquest.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WorkoutPlanTitleAlreadyExistsException extends ResponseStatusException {
    public WorkoutPlanTitleAlreadyExistsException() {

        super(HttpStatus.BAD_REQUEST, "WORKOUT_PLAN_TITLE_ALREADY_EXISTS");
    }
}
