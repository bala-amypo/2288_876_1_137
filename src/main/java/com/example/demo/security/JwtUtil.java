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
