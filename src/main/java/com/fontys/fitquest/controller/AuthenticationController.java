package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.service.AuthenticationService;
import com.fontys.fitquest.domain.requests.LoginRequest;
import com.fontys.fitquest.domain.responses.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody @Valid LoginRequest request) {
        authenticationService.createUser(request);

        LoginResponse loginResponse = authenticationService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}