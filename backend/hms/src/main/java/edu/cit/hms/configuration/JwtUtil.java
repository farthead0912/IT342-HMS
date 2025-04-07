package edu.cit.hms.configuration;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "jvnHumxyKgHSNbWb7SoFnWcW5nD0dGCx"; // testing purposes, this is 256 bits and encoded in HS256
    private static final long EXPIRATION_TIME = 86400000;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username, String role, int userId) {
        return Jwts.builder()
                    .subject(username)
                    .claim("role", role)
                    .claim("id", userId)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(key)
                    .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }
}
