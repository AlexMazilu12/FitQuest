package com.fontys.fitquest.domain.requests;
import com.fontys.fitquest.domain.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Role role;
    private String name;
}

