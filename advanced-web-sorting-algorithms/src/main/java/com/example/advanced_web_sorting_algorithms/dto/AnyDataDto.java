package com.example.advanced_web_sorting_algorithms.dto;

import jakarta.validation.constraints.NotBlank;

public record AnyDataDto(
        @NotBlank(message = "Data is required")
        String data,
        Integer id
) {
}
