package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.*;
import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.domain.requests.CreateUserRequest;
import com.fontys.fitquest.domain.requests.GetAllUsersRequest;
import com.fontys.fitquest.domain.requests.UpdateUserRequest;
import com.fontys.fitquest.domain.responses.CreateUserResponse;
import com.fontys.fitquest.domain.responses.GetAllUsersResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final GetUserUseCase getUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id) {
        final Optional<User> userOptional = getUserUseCase.getUser(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers(@RequestParam(value = "email", required = false) String email) {
        GetAllUsersRequest request = GetAllUsersRequest.builder().email(email).build();
        GetAllUsersResponse response = getUsersUseCase.getUsers(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateUserRequest request) {
        request.setId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        GetAllUsersResponse response = getUsersUseCase.getUsers(request);
        List<User> users = response.getUsers();
        return ResponseEntity.ok(users);
    }
}
