package com.fontys.fitquest.configuration.token;

import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.util.Base64;

public class SecretKeyGenerator {
    private static final Logger logger = LoggerFactory.getLogger(SecretKeyGenerator.class);

    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        logger.info("Base64-encoded secret key: {}", base64Key);
    }
}