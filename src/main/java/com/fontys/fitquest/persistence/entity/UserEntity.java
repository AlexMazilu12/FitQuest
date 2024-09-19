package com.fontys.fitquest.persistence.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserEntity {
    private Long id;
    private String name;
    private String email;
    private String password;
}
