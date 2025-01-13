package com.fontys.fitquest.domain.requests;

import com.fontys.fitquest.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long id;

    @Email
    @NotBlank
    private String email;


    @NotNull
    private Role role;


    @NotBlank
    private String name;
}
