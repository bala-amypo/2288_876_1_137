package com.example.demo.security;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    private static final String SECRET_KEY = "saas-secret-key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ✅ REQUIRED: no-argument constructor (SAAS rule)
    public JwtUtil() {
    }

    // SAAS REQUIRED METHOD
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // SAAS REQUIRED METHOD
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // SAAS REQUIRED METHOD
    public boolean isTokenValid(String token, String username) {
        return username.equals(getUsername(token)) && !isTokenExpired(token);
    }

    // SAAS REQUIRED METHOD
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public long getExpirationMillis() {
        return EXPIRATION_TIME;
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}

// package com.example.demo.security;

// import java.util.Date;
// import java.util.Map;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// public class JwtUtil {

//     private static final String SECRET_KEY = "saas-secret-key";
//     private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

//     // ✅ REQUIRED: no-argument constructor (SAAS rule)
//     public JwtUtil() {
//     }

//     // SAAS REQUIRED METHOD
//     public String generateToken(Map<String, Object> claims, String subject) {
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                 .compact();
//     }

//     // SAAS REQUIRED METHOD
//     public String getUsername(String token) {
//         return getClaims(token).getSubject();
//     }

//     // SAAS REQUIRED METHOD
//     public boolean isTokenValid(String token, String username) {
//         return username.equals(getUsername(token)) && !isTokenExpired(token);
//     }

//     // SAAS REQUIRED METHOD
//     public Claims getClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(SECRET_KEY)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public long getExpirationMillis() {
//         return EXPIRATION_TIME;
//     }

//     private boolean isTokenExpired(String token) {
//         return getClaims(token).getExpiration().before(new Date());
//     }
// }
