package com.ws101.arce.ecommerceapi.controller;

import com.ws101.arce.ecommerceapi.dto.RegisterUserDto;
import com.ws101.arce.ecommerceapi.model.User;
import com.ws101.arce.ecommerceapi.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterUserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password())); // Encrypts plain text password entry
        user.setRole(dto.role() != null ? dto.role() : "ROLE_USER");
        
        userRepository.save(user);
        return "User account registered successfully!";
    }
}