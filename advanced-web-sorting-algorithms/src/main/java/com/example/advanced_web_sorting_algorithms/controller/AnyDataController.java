package com.example.advanced_web_sorting_algorithms.controller;

import com.example.advanced_web_sorting_algorithms.dto.AnyDataDto;
import com.example.advanced_web_sorting_algorithms.entity.AnyData;
import com.example.advanced_web_sorting_algorithms.service.AnyDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/any-data")
@RequiredArgsConstructor
public class AnyDataController {

    private final AnyDataService anyDataService;

    @PostMapping
    public ResponseEntity<String> saveData(@Valid @RequestBody AnyDataDto anyDataDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(anyDataService.saveData(anyDataDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable Integer id) {
        return ResponseEntity.ok(anyDataService.deleteData(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateData(@PathVariable Integer id, @Valid @RequestBody AnyDataDto anyDataDto) {
        return ResponseEntity.ok(anyDataService.updateData(id, anyDataDto));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<AnyData>>> getAllData() {
        return ResponseEntity.ok(anyDataService.getAllData());
    }

}
