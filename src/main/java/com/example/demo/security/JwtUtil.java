package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private final String SECRET_KEY = "secret-key-for-testing";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ✅ MUST HAVE NO-ARG CONSTRUCTOR
    public JwtUtil() {
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public long getExpirationMillis() {
        return EXPIRATION_TIME;
    }

    public boolean isTokenValid(String token, String username) {
        String extractedUsername = getUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
