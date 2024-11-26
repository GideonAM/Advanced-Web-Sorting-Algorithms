package com.example.advanced_web_sorting_algorithms.dto;

import jakarta.validation.constraints.NotBlank;

public record SortDataDto(
        @NotBlank(message = "Data to be sorted is required")
        String sortData,
        @NotBlank(message = "Sorting algorithm is required")
        String algorithm
) {
}
