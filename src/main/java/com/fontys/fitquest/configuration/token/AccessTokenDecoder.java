package com.fontys.fitquest.configuration.token;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}