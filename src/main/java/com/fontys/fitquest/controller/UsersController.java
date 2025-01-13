package com.fontys.fitquest.controller;

import com.fontys.fitquest.business.*;
import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.domain.requests.CreateUserRequest;
import com.fontys.fitquest.domain.requests.GetAllUsersRequest;
import com.fontys.fitquest.domain.requests.UpdateUserRequest;
import com.fontys.fitquest.domain.responses.CreateUserResponse;
import com.fontys.fitquest.domain.responses.GetAllUsersResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Validated
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
                                           @RequestBody @Valid UpdateUserRequest request,
                                           Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin || userDetails.getUsername().equals(request.getEmail())) {
            request.setId(id);
            updateUserUseCase.updateUser(request);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/all")
    @RolesAllowed({"TRAINER","ADMIN"})
    public ResponseEntity<List<User>> getAllUsers() {
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        GetAllUsersResponse response = getUsersUseCase.getUsers(request);
        List<User> users = response.getUsers();
        return ResponseEntity.ok(users);
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/trainers")
    public ResponseEntity<GetAllUsersResponse> getTrainers() {
        GetAllUsersResponse response = getUsersUseCase.getUsers(new GetAllUsersRequest());
        return ResponseEntity.ok(response);
    }
}
