package com.example.advanced_web_sorting_algorithms.controller;

import com.example.advanced_web_sorting_algorithms.dto.AuthenticationDto;
import com.example.advanced_web_sorting_algorithms.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthenticationDto authenticationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(authenticationDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthenticationDto authenticationDto) {
        return ResponseEntity.ok(authenticationService.login(authenticationDto));
    }

}
