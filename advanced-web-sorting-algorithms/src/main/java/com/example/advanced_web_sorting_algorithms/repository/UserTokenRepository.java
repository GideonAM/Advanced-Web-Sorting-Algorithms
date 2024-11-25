package com.example.advanced_web_sorting_algorithms.repository;

import com.example.advanced_web_sorting_algorithms.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {
    Optional<UserToken> findByToken(String token);
}
