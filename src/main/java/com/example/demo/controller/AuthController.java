package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.service.AuthService;
import com.example.demo.web.dto.AuthRequestDto;
import com.example.demo.web.dto.RegisterRequestDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDto request) {
        return authService.login(request);
    }
}
