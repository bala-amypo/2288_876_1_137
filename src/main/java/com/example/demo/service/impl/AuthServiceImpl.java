package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserAccountRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequestDto dto) {
        UserAccount user = new UserAccount();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);

        userRepo.save(user);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto dto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        // SAAS-compliant token generation
        Map<String, Object> claims = new HashMap<>();

        String token = jwtUtil.generateToken(
                claims,
                dto.getEmail()
        );

        return new AuthResponseDto(token);
    }
}
