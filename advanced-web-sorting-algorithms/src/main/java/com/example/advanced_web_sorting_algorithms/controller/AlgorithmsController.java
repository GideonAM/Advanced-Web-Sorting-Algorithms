package com.example.advanced_web_sorting_algorithms.controller;

import com.example.advanced_web_sorting_algorithms.dto.SortDataDto;
import com.example.advanced_web_sorting_algorithms.service.AlgorithmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/algorithms")
@RequiredArgsConstructor
public class AlgorithmsController {

    private final AlgorithmsService algorithmsService;

    @PostMapping
    public ResponseEntity<String> sortWebData(@Valid @RequestBody SortDataDto sortDataDto) {
        return ResponseEntity.ok(algorithmsService.sortWebData(sortDataDto));
    }

    @GetMapping
    public ResponseEntity<List<String>> findAllAlgorithms() {
        return ResponseEntity.ok(algorithmsService.findAllAlgorithms());
    }

}
