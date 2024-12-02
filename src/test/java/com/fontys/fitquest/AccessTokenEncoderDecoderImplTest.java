package com.fontys.fitquest;

import com.fontys.fitquest.business.exception.InvalidAccessTokenException;
import com.fontys.fitquest.configuration.token.AccessToken;
import com.fontys.fitquest.configuration.token.impl.AccessTokenEncoderDecoderImpl;
import com.fontys.fitquest.domain.Role;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenEncoderDecoderImplTest {

    private AccessTokenEncoderDecoderImpl encoderDecoder;
    private Key key;

    @BeforeEach
    void setUp() {
        String secretKey = "mysecretkeymysecretkeymysecretkeymysecretkey";
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
        encoderDecoder = new AccessTokenEncoderDecoderImpl(secretKey);
    }

    @Test
    void encodeDecode_success() {
        AccessToken accessToken = new AccessToken() {
            @Override
            public String getEmail() {
                return "email@example.com";
            }

            @Override
            public Long getUserId() {
                return 1L;
            }

            @Override
            public Role getRole() {
                return Role.USER;
            }

            @Override
            public Boolean hasRole(String roleName) {
                return Role.USER.name().equals(roleName);
            }
        };
        String encodedToken = encoderDecoder.encode(accessToken);

        AccessToken decodedToken = encoderDecoder.decode(encodedToken);

        assertNotNull(decodedToken);
        assertEquals(accessToken.getEmail(), decodedToken.getEmail());
        assertEquals(accessToken.getUserId(), decodedToken.getUserId());
        assertEquals(accessToken.getRole(), decodedToken.getRole());
    }

    @Test
    void decode_invalidToken() {
        String invalidToken = "invalidToken";

        assertThrows(InvalidAccessTokenException.class, () -> encoderDecoder.decode(invalidToken));
    }
}