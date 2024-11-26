package com.example.advanced_web_sorting_algorithms.service.impl;

import com.example.advanced_web_sorting_algorithms.dto.AuthenticationDto;
import com.example.advanced_web_sorting_algorithms.entity.User;
import com.example.advanced_web_sorting_algorithms.entity.UserToken;
import com.example.advanced_web_sorting_algorithms.repository.UserRepository;
import com.example.advanced_web_sorting_algorithms.repository.UserTokenRepository;
import com.example.advanced_web_sorting_algorithms.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserTokenRepository userTokenRepository;
    @Mock
    private AuthenticationProvider authenticationProvider;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    private AuthenticationDto authenticationDto;
    private User user;

    @BeforeEach
    public void init() {
        authenticationDto =
                new AuthenticationDto("email@gmail.com", "password");
        user = User.builder()
                .email("email@gmail.com")
                .password("password")
                .build();
    }

    @Test
    void register() {
        when(userRepository.save(user)).thenReturn(user);
        when(passwordEncoder.encode(authenticationDto.password())).thenReturn("password");

        String response = authenticationService.register(authenticationDto);
        assertThat(response).isEqualTo("Account created successfully");
    }

    @Test
    void login() {
        String token = "jwt";
        UserToken userToken = UserToken.builder()
                ._user(user)
                .token(token)
                .build();

        when(authenticationProvider.authenticate(any(Authentication.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal())
                .thenReturn(user);
        when(jwtService.generateToken(authenticationDto.email()))
                .thenReturn(token);

        when(userTokenRepository.save(userToken))
                .thenReturn(userToken);
        String loginResponse = authenticationService.login(authenticationDto);

        assertThat(loginResponse).isEqualTo(token);
    }
}
