package com.fontys.fitquest.persistence.entity;

import com.fontys.fitquest.domain.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserEntity {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
