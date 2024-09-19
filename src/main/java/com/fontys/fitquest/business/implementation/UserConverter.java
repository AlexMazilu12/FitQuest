package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.domain.User;
import com.fontys.fitquest.persistence.entity.UserEntity;

public class UserConverter {
    private UserConverter() {
    }

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
