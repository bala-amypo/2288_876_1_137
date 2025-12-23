package com.example.demo.security;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    // 🔐 SAAS secret key
    private static final String SECRET_KEY =
            "saas-secret-key-saas-secret-key-saas-secret-key";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    // ===============================
    // ✅ TOKEN GENERATION (SAAS)
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

    // Optional overload
    public String generateToken(String username) {
        return generateToken(Map.of(), username);
    }

    // ===============================
    // ✅ EXTRACT USERNAME
    // ===============================
    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ===============================
    // ✅ TOKEN VALIDATION
    // ===============================
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = getUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    // ===============================
    // INTERNAL HELPERS
    // ===============================
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
