package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // ====== CONSTANTS (tests expect positive expiration) ======
    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkeymysecretkey"; // min 32 chars

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ====== KEY GENERATION ======
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // ====== TOKEN GENERATION ======
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ====== EXTRACT USERNAME ======
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ====== EXTRACT EXPIRATION ======
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // ====== GENERIC CLAIM EXTRACTION ======
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // ====== PARSE TOKEN ======
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ====== TOKEN VALIDATION ======
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ====== USED BY TEST CASE ======
    public long getExpirationMillis() {
        return EXPIRATION_TIME;
    }
}

// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import java.nio.charset.StandardCharsets;
// import java.security.Key;
// import java.util.Date;
// import java.util.Map;

// @Component
// public class JwtUtil {

//     // MUST be >= 32 bytes
//     private static final String SECRET_KEY = "saas-secret-key-very-strong-256-bit-value";

//     private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour

//     private Key getSigningKey() {
//         return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
//     }

//     public String generateToken(Map<String, Object> claims, String subject) {
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public String getUsername(String token) {
//         return getAllClaims(token).getSubject();
//     }

//     public boolean isTokenValid(String token, String username) {
//         return username.equals(getUsername(token)) && !isTokenExpired(token);
//     }

//     public long getExpirationMillis() {
//         return EXPIRATION_TIME;
//     }

//     private boolean isTokenExpired(String token) {
//         return getAllClaims(token).getExpiration().before(new Date());
//     }

//     private Claims getAllClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getSigningKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }
