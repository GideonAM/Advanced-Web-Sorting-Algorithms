package com.example.advanced_web_sorting_algorithms.config;

import com.example.advanced_web_sorting_algorithms.entity.UserToken;
import com.example.advanced_web_sorting_algorithms.repository.UserTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogoutHandler implements LogoutSuccessHandler {

    private final UserTokenRepository userTokenRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) {
        var header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            try {
                new ObjectMapper().writeValue(response.getOutputStream(), "Failed to logout");
            } catch (IOException e) {
                throw new RuntimeException("Failed to logout");
            }
            return;
        }

        var token = header.substring(7);
        deleteAllUserTokens(token);
        try {
            new ObjectMapper().writeValue(response.getOutputStream(), "Logout successfully");
        } catch (IOException e) {
            throw new RuntimeException("Logout successfully");
        }
    }

    private void deleteAllUserTokens(String token) {
        UserToken userToken = userTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Failed to logout"));

        List<UserToken> allUserTokens = userTokenRepository.getAllUserTokens(userToken.getUserData().getId());
        userTokenRepository.deleteAll(allUserTokens);
    }
}
