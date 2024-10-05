package com.example.user_service.service.impl;

import com.example.user_service.util.JwtUtil;
import com.example.user_service.dto.LoginRequestDTO;
import com.example.user_service.dto.UserDTO;
import com.example.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    // Test Users
    private static final List<UserDTO> USERS = Arrays.asList(new UserDTO(1, "test@gmail.com", "password"));
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        Optional<UserDTO> userDTO = USERS.stream()
                                         .filter(user -> user.getEmail().equals(loginRequestDTO.getEmail())
                                                 && user.getPassword().equals(loginRequestDTO.getPassword()))
                                         .findFirst();

        if (userDTO.isEmpty()) {
            throw new RuntimeException("Invalid credentials!");
        }

        return jwtUtil.createToken(userDTO.get().getId());
    }
}
