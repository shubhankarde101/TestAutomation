package com.apple.phoenix.MyCode;

import java.util.ArrayList;
import java.util.List;

public class ArrayCombinations {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6};
        int target = 6;
        List<List<Integer>> result = generateAllCombinations(arr, target);
        result.stream().forEach(x -> System.out.println(x));
    }

    public static List<List<Integer>> generateAllCombinations(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        generateCombinationsHelper(nums, 0, new ArrayList<Integer>(), result, target);
        return result;
    }

    private static void generateCombinationsHelper(int[] nums, int start, List<Integer> currentList, List<List<Integer>> result, int target) {
        if (currentList.stream().mapToInt(Integer::intValue).sum() == target)
            result.add(new ArrayList<>(currentList));

        for (int i = start; i < nums.length; i++) {
            currentList.add(nums[i]); // Add the next element to the current combination
            generateCombinationsHelper(nums, i + 1, currentList, result, target); // Recursively generate all combinations with the next element
            currentList.remove(currentList.size() - 1); // Remove the last element to backtrack and generate the next combination
        }
    }
}
