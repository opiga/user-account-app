package com.example.my_app.repository;

import com.example.my_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmails_Email(String email);
    Optional<User> findByPhones_Phone(String phone);
    
}
