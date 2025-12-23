package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // ✅ REQUIRED BY SAAS
    public String generateToken(String username) {
        return "dummy-token-for-" + username;
    }

    // ✅ REQUIRED BY SAAS
    public String extractUsername(String token) {
        if (token == null) return null;
        return token.replace("dummy-token-for-", "");
    }

    // ✅ REQUIRED BY SAAS
    public boolean isTokenValid(String token, String username) {
        return token != null && token.contains(username);
    }
}
