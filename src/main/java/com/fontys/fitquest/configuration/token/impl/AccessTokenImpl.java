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
    private final Boolean verified;

    public AccessTokenImpl(String email, Long userId, Role role, boolean verified) {
        this.email = email;
        this.userId = userId;
        this.role = role;
        this.verified = verified;
    }

    @Override
    public Boolean hasRole(String roleName) {
        return this.role.toString().equals(roleName);
    }
}