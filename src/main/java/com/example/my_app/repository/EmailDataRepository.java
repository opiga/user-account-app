package com.example.my_app.repository;

import com.example.my_app.entity.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
    Optional<EmailData> findByEmail(String email);
    boolean existsByEmail(String email);
}
