package com.example.advanced_web_sorting_algorithms.service.impl;

import com.example.advanced_web_sorting_algorithms.dto.AnyDataDto;
import com.example.advanced_web_sorting_algorithms.entity.AnyData;
import com.example.advanced_web_sorting_algorithms.hateoas.AnyDataAssembler;
import com.example.advanced_web_sorting_algorithms.repository.AnyDataRepository;
import com.example.advanced_web_sorting_algorithms.service.AnyDataService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnyDataServiceImpl implements AnyDataService {

    private final AnyDataRepository anyDataRepository;
    private final AnyDataAssembler anyDataAssembler;

    @Override
    public String saveData(AnyDataDto anyDataDto) {
        AnyData data = AnyData.builder()
                .data(anyDataDto.data())
                .build();
        anyDataRepository.save(data);

        return "Data created successfully";
    }

    @Override
    public String deleteData(Integer id) {
        AnyData data = anyDataRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Data not found for deletion"));
        anyDataRepository.delete(data);
        return "Data deleted successfully";
    }

    @Override
    public String updateData(Integer id, AnyDataDto anyDataDto) {
        AnyData data = anyDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Data not found to be updated"));
        data.setData(anyDataDto.data());
        anyDataRepository.save(data);

        return "Data updated successfully";
    }

    @Override
    public List<EntityModel<AnyData>> getAllData() {
        return anyDataRepository.findAll()
                .stream()
                .map(anyDataAssembler::toModel)
                .toList();
    }
}
