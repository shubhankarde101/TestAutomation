package com.apple.phoenix.MyCode;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClosestNumbers {

    public static void main(String[] args) {
         int[] arr = {6,2,4,10};
         int[] arr1 = {4,2,1,3};

        System.out.println("Checking for Minimum absolute difference");
        closestNumbers(arr1);
    }
    private static int closestNumbers(int[] arr) {
        int minimum = 999999;
        List<Integer> li = new ArrayList<>();
        if (arr.length == 0 || arr.length == 1) {
            System.out.println("No solution exist");
        }else {
            for (int i = 0; i < arr.length; i++) {
                for(int j=i+1;j<arr.length;j++)
                {
                    int diff = Math.abs(arr[i]-arr[j]);
                    if(minimum>diff)
                    {
                        minimum = diff;
                    }
                }
            }
            for (int i = 0; i < arr.length; i++) {
                for(int j=i+1;j<arr.length;j++)
                {
                    int diff = Math.abs(arr[i]-arr[j]);
                    if(diff==minimum)
                    {
                        li.add(arr[i]);
                        li.add(arr[j]);

                    }
                }
            }
        }
        Collections.sort(li);
        for(int i=0;i<li.size()-1;i=i+2)
        {
            System.out.println(String.valueOf(li.get(i))+" "+li.get(i+1));
        }
        System.out.println("Minimum difference is: "+minimum);
        return minimum;
    }
}
