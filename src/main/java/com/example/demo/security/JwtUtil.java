// package com.example.demo.security;

// import java.util.Date;
// import java.util.Map;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// public class JwtUtil {

//     private final String SECRET = "saas-secret-key";
//     private final long EXPIRATION = 1000 * 60 * 60;

//     // REQUIRED: no-arg constructor
//     public JwtUtil() {}

//     public String generateToken(Map<String, Object> claims, String subject) {
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                 .signWith(SignatureAlgorithm.HS256, SECRET)
//                 .compact();
//     }

//     public String getUsername(String token) {
//         return getClaims(token).getSubject();
//     }

//     public boolean isTokenValid(String token, String username) {
//         return username.equals(getUsername(token)) && !isTokenExpired(token);
//     }

//     public Claims getClaims(String token) {
//         return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
//     }

//     public long getExpirationMillis() {
//         return EXPIRATION;
//     }

//     private boolean isTokenExpired(String token) {
//         return getClaims(token).getExpiration().before(new Date());
//     }
// }
package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    // ✅ SAAS fixed secret (minimum 256-bit)
    private static final Key SECRET_KEY =
            Keys.hmacShaKeyFor("saas-demo-secret-key-which-is-very-secure-12345"
                    .getBytes());

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // ✅ USED BY AuthServiceImpl
    public String generateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ REQUIRED BY JwtAuthFilter
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ REQUIRED BY JwtAuthFilter
    public boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // 🔐 INTERNAL HELPERS
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
