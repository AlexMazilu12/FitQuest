package com.fontys.fitquest.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private Long userId;
    private String name;
    private String email;
    private String role;
}
