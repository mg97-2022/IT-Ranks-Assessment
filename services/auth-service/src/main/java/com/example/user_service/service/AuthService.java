package com.example.user_service.service;

import com.example.user_service.dto.LoginRequestDTO;

public interface AuthService {
    String login(LoginRequestDTO loginRequestDTO);
}
