package com.example.advanced_web_sorting_algorithms.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {
    public static int[] radixSort(int [] array, int radix, int width){
        for (int i = 0; i < width; i++){
            radixSingleSort(array, i, radix);
        }

        return array;
    }

    private static void radixSingleSort(int [] array, int position, int radix){
        int numItems = array.length;
        int [] countArray = new int[radix];

        for (int value: array){
            countArray[getDigit(position, value, radix)]++;
        }

        for (int j = 1; j < radix; j++){
            countArray[j] += countArray[j - 1];
        }

        int [] temp = new int[numItems];
        for (int tempIndex = numItems - 1; tempIndex >= 0; tempIndex--){
            temp[--countArray[getDigit(position, array[tempIndex], radix)]] =
                    array[tempIndex];
        }

        System.arraycopy(temp, 0, array, 0, numItems);
    }

    private static int getDigit(int position, int value, int radix){
        return value / (int) Math.pow(10, position) % radix;
    }

    public static int[] mergeSort(int [] array){
        int length = array.length;
        if(length <= 1) return new int[0];
        int middle = length / 2;
        int [] leftArray = new int[middle];
        int [] rightArray = new int[length - middle];

        int i = 0;
        int j = 0;

        for (; i < length; i++){
            if (i < middle){
                leftArray[i] = array[i];
            }else {
                rightArray[j] = array[i];
                j++;
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);

        return array;
    }

    private static void merge(int [] leftArray, int [] rightArray, int [] array){

        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0;

        while (l < leftSize && r < rightSize){
            if (leftArray[l] < rightArray[r]){
                array[i] = leftArray[l];
                i++;
                l++;
            }else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while (l < leftSize){
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while (r < rightSize){
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }

    public static int[] quickSort(int [] array, int start, int end){

        if (end < start) return new int[0];
        int pivot = partition(array, start, end);
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);

        return array;
    }

    private static int partition(int [] array, int start, int end){

        int pivot = array[end];
        int i = start - 1;

        for (int j = start; j <= end - 1; j++){
            if (array[j] < pivot){
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        i++;
        int temp = array[i];
        array[i] = array[end];
        array[end] = temp;

        return i;
    }

    public static int[] heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }

        return arr;
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public static float[] bucketSort(float[] arr) {
        int n = arr.length;

        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int bi = (int) (n * arr[i]);
            buckets[bi].add(arr[i]);
        }

        for (int i = 0; i < n; i++) {
            insertionSort(buckets[i]);
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }

        return arr;
    }

    public static void insertionSort(List<Float> bucket) {
        for (int i = 1; i < bucket.size(); ++i) {
            float key = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }
}
