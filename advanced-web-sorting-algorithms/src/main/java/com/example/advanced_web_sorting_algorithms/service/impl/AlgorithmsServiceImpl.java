package com.example.advanced_web_sorting_algorithms.service.impl;

import com.example.advanced_web_sorting_algorithms.algorithms.AlgorithmValues;
import com.example.advanced_web_sorting_algorithms.algorithms.Algorithms;
import com.example.advanced_web_sorting_algorithms.dto.SortDataDto;
import com.example.advanced_web_sorting_algorithms.service.AlgorithmsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AlgorithmsServiceImpl implements AlgorithmsService {

    @Override
    public String sortWebData(SortDataDto sortDataDto) {
        List<String> allAlgorithms = findAllAlgorithms();

        if (allAlgorithms.contains(sortDataDto.algorithm())) {

            if (sortDataDto.algorithm().equals("BUCKET_SORT")) {
                try {
                    Float[] floatArray = convertInputDataToFloatArray(sortDataDto.sortData());
                    return sortFloat(floatArray);
                } catch (Exception exception) {
                    throw new RuntimeException("Float digits with single spacing should be used for bucket sort algorithm");
                }
            }

            try {
                int[] intArray = convertInputDataToIntArray(sortDataDto.sortData());
                var selectedAlgorithm = sortDataDto.algorithm();

                return sortedIntArray(intArray, selectedAlgorithm);
            } catch (Exception exception) {
                throw new RuntimeException("Input data should be whole numbers only and should be separated by single space");
            }
        }

        return "Selected algorithm not found";
    }

    @Override
    public List<String> findAllAlgorithms() {
        return Arrays.stream(AlgorithmValues.values())
                .map(AlgorithmValues::name).toList();
    }

    private String sortedIntArray(int[] intArray, String selectedAlgorithm) {
        int[] sorted = sortInt(selectedAlgorithm, intArray);
        StringBuilder builder = new StringBuilder();

        for (var item : sorted)
            builder.append(item).append(" ");

        return builder.toString();
    }

    private Float[] convertInputDataToFloatArray(String inputData) {
        String[] splitData = inputData.split(" ");

        return Arrays.stream(splitData)
                .map(Float::valueOf)
                .toArray(Float[]::new);
    }

    private int[] convertInputDataToIntArray(String inputData) {
        String[] splitData = inputData.split(" ");

        return Arrays.stream(splitData)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private String sortFloat(Float[] array) {
        float[] temp = new float[array.length];
        for (int i = 0; i < array.length; i++)
            temp[i] = array[i];

        float[] outputs = Algorithms.bucketSort(temp);
        StringBuilder builder = new StringBuilder();

        for (var item : outputs)
            builder.append(item).append(" ");
        return builder.toString();
    }

    private int[] sortInt(String algorithm, int[] array) {
        int[] result;
        int max = Arrays.stream(array).max().orElse(1);

        result = switch (algorithm.toLowerCase()) {
            case "heap_sort" -> Algorithms.heapSort(array);
            case "quick_sort" -> Algorithms.quickSort(array, 0, array.length - 1);
            case "merge_sort" -> Algorithms.mergeSort(array);
            case "radix_sort" -> Algorithms.radixSort(array, 10, max);
            default -> new int[0];
        };

        return result;
    }

}
