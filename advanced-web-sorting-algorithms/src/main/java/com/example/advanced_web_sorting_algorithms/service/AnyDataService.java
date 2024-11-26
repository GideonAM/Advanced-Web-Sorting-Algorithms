package com.example.advanced_web_sorting_algorithms.service;

import com.example.advanced_web_sorting_algorithms.dto.AnyDataDto;
import com.example.advanced_web_sorting_algorithms.entity.AnyData;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface AnyDataService {
    String saveData(@Valid AnyDataDto anyDataDto);

    String deleteData(Integer id);

    String updateData(Integer id, AnyDataDto anyDataDto);

    List<EntityModel<AnyData>> getAllData();
}
