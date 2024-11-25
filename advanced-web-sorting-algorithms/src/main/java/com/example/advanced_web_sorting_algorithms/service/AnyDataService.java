package com.example.advanced_web_sorting_algorithms.service;

import com.example.advanced_web_sorting_algorithms.dto.AnyDataDto;
import jakarta.validation.Valid;

import java.util.List;

public interface AnyDataService {
    String saveData(@Valid AnyDataDto anyDataDto);

    String deleteData(Integer id);

    String updateData(Integer id, AnyDataDto anyDataDto);

    List<AnyDataDto> getAllData();
}
