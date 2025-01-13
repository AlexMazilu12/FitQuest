package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.CreateUserTrainerRelationUseCase;
import com.fontys.fitquest.business.DeleteUserTrainerRelationUseCase;
import com.fontys.fitquest.business.GetUsersForTrainerUseCase;
import com.fontys.fitquest.domain.requests.CreateUserTrainerRelationRequest;
import com.fontys.fitquest.domain.responses.CreateUserTrainerRelationResponse;
import com.fontys.fitquest.domain.responses.GetUsersForTrainerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/user-trainer-relations")
@Validated
public class TrainerUserRelationController {

    private static final Logger logger = LoggerFactory.getLogger(TrainerUserRelationController.class);

    @Autowired
    private CreateUserTrainerRelationUseCase createUserTrainerRelationUseCase;

    @Autowired
    private DeleteUserTrainerRelationUseCase deleteUserTrainerRelationUseCase;

    @Autowired
    private GetUsersForTrainerUseCase getUsersForTrainerUseCase;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserTrainerRelationResponse> createUserTrainerRelation(@RequestBody CreateUserTrainerRelationRequest request) {
        CreateUserTrainerRelationResponse response = createUserTrainerRelationUseCase.createUserTrainerRelation(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/trainer/{trainerId}/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUserTrainerRelation(@PathVariable Long trainerId, @PathVariable Long userId) {
        logger.info("Deleting user-trainer relation with trainer_id: {} and user_id: {}", trainerId, userId);
        deleteUserTrainerRelationUseCase.deleteUserTrainerRelation(trainerId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/trainer/{trainerId}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetUsersForTrainerResponse>> getUsersForTrainer(@PathVariable Long trainerId) {
        List<GetUsersForTrainerResponse> users = getUsersForTrainerUseCase.getUsersForTrainer(trainerId);
        return ResponseEntity.ok(users);
    }
}