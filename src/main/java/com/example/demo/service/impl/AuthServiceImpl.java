package com.example.demo.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.web.dto.AuthRequestDto;
import com.example.demo.web.dto.AuthResponseDto;
import com.example.demo.web.dto.RegisterRequestDto;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // ✅ SAAS-required constructor
    public AuthServiceImpl(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil) {

        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequestDto request) {

        if (userAccountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserAccount user = new UserAccount();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        userAccountRepository.save(user);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserAccount user = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ FIX: SAAS JwtUtil takes ONLY username
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDto(token);
    }
}
