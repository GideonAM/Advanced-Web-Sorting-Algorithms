package com.example.advanced_web_sorting_algorithms.service.impl;

import com.example.advanced_web_sorting_algorithms.dto.AuthenticationDto;
import com.example.advanced_web_sorting_algorithms.entity.User;
import com.example.advanced_web_sorting_algorithms.entity.UserToken;
import com.example.advanced_web_sorting_algorithms.repository.UserRepository;
import com.example.advanced_web_sorting_algorithms.repository.UserTokenRepository;
import com.example.advanced_web_sorting_algorithms.security.JwtService;
import com.example.advanced_web_sorting_algorithms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(AuthenticationDto authenticationDto) {
        User user = User.builder()
                .email(authenticationDto.email())
                .password(passwordEncoder.encode(authenticationDto.password()))
                .build();
        userRepository.save(user);

        return "Account created successfully";
    }

    @Override
    public String login(AuthenticationDto authenticationDto) {
        Authentication authenticatedUser = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.email(), authenticationDto.password())
        );

        User user = (User) authenticatedUser.getPrincipal();
        var token = jwtService.generateToken(authenticationDto.email());

        UserToken userToken = UserToken.builder()
                .userData(user)
                .token(token)
                .build();
        userTokenRepository.save(userToken);
        return token;
    }
}
