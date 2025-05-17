package com.example.my_app.controller;

import com.example.my_app.dto.request.AuthRequest;
import com.example.my_app.dto.response.AuthResponse;
import com.example.my_app.entity.User;
import com.example.my_app.repository.UserRepository;
import com.example.my_app.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        
        User user;
        
        if (request.getEmail() != null) {
            user = userRepository.findByEmails_Email(request.getEmail())
                                 .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        } else if (request.getPhone() != null) {
            user = userRepository.findByPhones_Phone(request.getPhone())
                                 .orElseThrow(() -> new UsernameNotFoundException("Phone not found"));
        } else {
            throw new IllegalArgumentException("Email or phone is required");
        }
        
        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        
        String token = jwtUtil.generateToken(user.getId());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

