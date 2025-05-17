package com.example.my_app.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    
    private static final String SECRET_KEY = "secret_key";
    
    public String generateToken(Long userId) {
        return Jwts.builder()
                   .setSubject("auth")
                   .claim("userId", userId)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                   .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                   .compact();
    }
    
    public Long extractUserId(String token) {
        return Jwts.parser()
                   .setSigningKey(SECRET_KEY)
                   .parseClaimsJws(token)
                   .getBody()
                   .get("userId", Long.class);
    }
}