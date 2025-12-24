package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET_KEY = "saas-secret-key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ===============================
    // Generate token
    // ===============================
    public String generateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // ===============================
    // Extract username
    // ===============================
    public String getUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ===============================
    // Validate token
    // ===============================
    public boolean isTokenValid(String token, String username) {
        String tokenUsername = getUsername(token);
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }

    // ===============================
    // Expiration helpers
    // ===============================
    public long getExpirationMillis() {
        return EXPIRATION_TIME;
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
