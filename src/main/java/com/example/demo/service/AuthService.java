package com.example.demo.service;

import com.example.demo.web.dto.AuthRequestDto;
import com.example.demo.web.dto.RegisterRequestDto;

public interface AuthService {

    void register(RegisterRequestDto request);

    String login(AuthRequestDto request);
}
