package com.example.advanced_web_sorting_algorithms.service;

import com.example.advanced_web_sorting_algorithms.dto.AuthenticationDto;
import jakarta.validation.Valid;

public interface AuthenticationService {
    String register(@Valid AuthenticationDto authenticationDto);

    String login(@Valid AuthenticationDto authenticationDto);
}
