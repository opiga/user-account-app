package com.example.my_app.dto.request;

import lombok.Data;
import lombok.Getter;

@Getter
public class AuthRequest {
    private String email;
    private String phone;
    private String password;
}