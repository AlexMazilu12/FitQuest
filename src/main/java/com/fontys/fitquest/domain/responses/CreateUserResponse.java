package com.fontys.fitquest.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private Long userId;
    private String email;
    private String role;
    private String name;
}
