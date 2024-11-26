package com.example.advanced_web_sorting_algorithms.service;

import com.example.advanced_web_sorting_algorithms.dto.SortDataDto;
import jakarta.validation.Valid;

import java.util.List;

public interface AlgorithmsService {
    String sortWebData(@Valid SortDataDto sortDataDto);

    List<String> findAllAlgorithms();
}
