package com.example.advanced_web_sorting_algorithms.repository;

import com.example.advanced_web_sorting_algorithms.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {
    Optional<UserToken> findByToken(String token);

    @Query(value = """
            SELECT ut
            FROM UserToken ut
            WHERE ut.userData.id = :userId
            """)
    List<UserToken> getAllUserTokens(Integer userId);
}
