package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.CreateUserTrainerRelationUseCase;
import com.fontys.fitquest.business.DeleteUserTrainerRelationUseCase;
import com.fontys.fitquest.domain.requests.CreateUserTrainerRelationRequest;
import com.fontys.fitquest.domain.responses.CreateUserTrainerRelationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/user-trainer-relations")
public class TrainerUserRelationController {

    @Autowired
    private CreateUserTrainerRelationUseCase createUserTrainerRelationUseCase;

    @Autowired
    private DeleteUserTrainerRelationUseCase deleteUserTrainerRelationUseCase;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserTrainerRelationResponse> createUserTrainerRelation(@RequestBody CreateUserTrainerRelationRequest request) {
        CreateUserTrainerRelationResponse response = createUserTrainerRelationUseCase.createUserTrainerRelation(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUserTrainerRelation(@PathVariable Long id) {
        deleteUserTrainerRelationUseCase.deleteUserTrainerRelation(id);
        return ResponseEntity.noContent().build();
    }
}