package com.fontys.fitquest.configuration.token;

import com.fontys.fitquest.domain.Role;

public interface AccessToken {
    String getEmail();

    Long getUserId();

    Role getRole();

    Boolean hasRole(String roleName);
}
