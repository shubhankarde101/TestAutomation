package com.apple.phoenix.MyCode;

public class MaxSubArraySum {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums1 = {4,-10,2,1};
        int[] result = findMaxSubarray(nums);

        System.out.println("Largest Sum: " + result[0]);
        System.out.print("SubArray: ");
        for (int i = result[1]; i <= result[2]; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static int[] findMaxSubarray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        int start = 0;
        int end = 0;
        int currentStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (currentSum + nums[i] < nums[i]) {
                currentSum = nums[i];
                currentStart = i;
            } else {
                currentSum += nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = currentStart;
                end = i;
            }
        }

        return new int[]{maxSum, start, end};
    }
}


