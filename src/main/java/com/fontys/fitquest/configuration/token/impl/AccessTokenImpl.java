package com.fontys.fitquest.configuration.token.impl;

import com.fontys.fitquest.configuration.token.AccessToken;
import com.fontys.fitquest.domain.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String email;
    private final Long userId;
    private final Role role;

    public AccessTokenImpl(String email, Long userId, Role role) {
        this.email = email;
        this.userId = userId;
        this.role = role;
    }

    @Override
    public Boolean hasRole(String roleName) {
        return this.role.toString().equals(roleName);
    }
}