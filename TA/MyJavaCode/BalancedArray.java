package com.apple.phoenix.MyCode;

import io.restassured.RestAssured;

public class BalancedArray {

    public static void main(String[] args) {

         int[] arr = {1,2,3,4,6};
        //int[] arr = {16,2,8,8};
        //int[] arr = {16, 17};
        //int[] arr = {16};
        //int[] arr = {};

        System.out.println("Checking for Balanced Array");
        int index = balancedArray(arr);
        try {
            System.out.println("The index of the smallest array element is: " + index);
            System.out.println("The smallest array element is: " + arr[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array exist but desired condition not met");
        }
    }

    private static int balancedArray(int[] arr) {

        int index = -1;
        if (arr.length == 0) {
            System.out.println("No array exist");
        } else if (arr.length == 1) {
            index = 0;
        } else {
            for (int i = 1; i < arr.length; i++) {
                int leftSum = sum(0, i - 1, arr);
                int rightSum = sum(i + 1, arr.length - 1, arr);
                if (leftSum == rightSum) {
                    index = i;
                    break;
                }
            }

        }
        return index;
    }
    private static int sum(int i, int i1, int[] arr) {
        int sumResult = 0;
        for (int j = i; j <= i1; j++) {
            sumResult = sumResult + arr[j];
        }
        return sumResult;
    }
}
