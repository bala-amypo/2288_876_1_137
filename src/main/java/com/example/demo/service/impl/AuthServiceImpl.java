package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserAccountRepository userAccountRepository,
                           Object passwordEncoderIgnored,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil) {
        this.userAccountRepository = userAccountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        UserAccount user = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid login"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());

        String token = jwtUtil.generateToken(claims, user.getEmail());

        return new AuthResponseDto(token);
    }

    @Override
    public void register(RegisterRequestDto dto) {

        if (userAccountRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
    }
}
