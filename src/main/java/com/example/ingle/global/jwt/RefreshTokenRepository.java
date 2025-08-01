package com.example.ingle.global.jwt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByStudentId(String studentId);
    void deleteByStudentId(String studentId);
}

