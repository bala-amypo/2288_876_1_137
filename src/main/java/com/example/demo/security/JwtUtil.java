package com.example.demo.security;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    // 🔐 SAAS REQUIRED CONSTANTS
    private static final String SECRET_KEY = "saas-secret-key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ✅ REQUIRED NO-ARG CONSTRUCTOR (SAAS)
    public JwtUtil() {}

    // ✅ REQUIRED BY AuthServiceImpl
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // ✅ REQUIRED BY JwtAuthFilter
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ REQUIRED BY JwtAuthFilter
    public boolean isTokenValid(String token, String username) {
        return username.equals(getUsername(token)) && !isTokenExpired(token);
    }

    // ✅ REQUIRED BY SAAS TESTS
    public long getExpirationMillis() {
        return EXPIRATION_TIME;
    }

    // ================= INTERNAL HELPERS =================

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }
}
