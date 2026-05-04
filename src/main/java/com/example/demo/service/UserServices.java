package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;

import com.example.demo.entity.User;

import com.example.demo.repository.UserRepository;

import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        //checking for duplicate emails
        if (userRepository.existsByEmail((request.getEmail()))){
            throw new RuntimeException("Email is already in use!");
        }

        //creating and saving new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
    }

    public String login (LoginRequest loginRequest) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Password incorrect/not found!");
        }

        return jwtUtil.generateToken(user);
    }
}
