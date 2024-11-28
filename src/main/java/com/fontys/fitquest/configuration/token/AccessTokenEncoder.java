package com.fontys.fitquest.configuration.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
