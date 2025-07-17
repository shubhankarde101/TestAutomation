package com.apple.phoenix.MyCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalogusArray {

/*
You have a secret array. You also have two integers upperbound and lowerbound.
Now you need to return how many arrays exist within the upperbound and lowerbound that analogy to your secret array.
Two arrays are analogy if their difference between consecutive numbers are the same.
For example, array [1, 0, 3, 0] and array [2, 1, 4, 1] are analogy. Because the difference are the same [1, -3, 3].
solve this in java by creating a function which will take an arrayList, an upper bound and a lower bound as an arguments
*/

    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(-1, -3, 2);
        System.out.println(countAnalogousArrays(li, 2, 8));
    }

    public static int countAnalogousArrays(List<Integer> diff, int lowerBound, int upperBound) {
        int count = 0;
        List<Integer> analogus = new ArrayList<>();
        for (int k = lowerBound; k < upperBound; k++) {
            int val = k;
            for (int i = 0; i < diff.size(); i++) {
                analogus.add(val);
                val = val - diff.get(i);
            }
            analogus.add(val);
            if (isAnalogus(analogus, lowerBound, upperBound)) {
                System.out.println(analogus);
                count++;
            }
            analogus.clear();
        }
        return count;
    }

    public static boolean isAnalogus(List<Integer> li, int lower, int upperBound) {
        return li.stream().allMatch(x -> x >= lower && x <= upperBound);
    }


}



