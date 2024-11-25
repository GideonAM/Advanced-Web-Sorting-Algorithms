package com.example.advanced_web_sorting_algorithms.repository;

import com.example.advanced_web_sorting_algorithms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String userEmail);
}
