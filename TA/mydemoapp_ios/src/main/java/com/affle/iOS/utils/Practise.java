package com.affle.iOS.utils;

import java.util.Arrays;

public class Practise {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3,3,3, 2, 4, 0};
        System.out.println("Original Array: " + Arrays.toString(arr));
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] == arr[j]) {
                    arr[j] = arr[len - 1];
                    len--;
                    j--;
                }
            }
        }
        int[] newArr = Arrays.copyOf(arr, len);
        System.out.println("Array with Duplicates Removed: " + Arrays.toString(newArr));
    }
}




