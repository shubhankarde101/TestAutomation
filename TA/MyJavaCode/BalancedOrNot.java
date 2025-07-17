package com.apple.phoenix.MyCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BalancedOrNot {

    public static void main(String[] args) {
        String[] arr = {"<<>>", "<>", "<><>", ">>", "<<>", "><><"};
        int[] replacement = {0, 1, 2, 2, 2, 2};
        System.out.println("Checking for array is balanced or not");
        balancedOrNot(arr, replacement);
    }

    private static void balancedOrNot(String[] arr, int[] replacement) {
        List<String> li = new ArrayList<>();
        int[] result = new int[arr.length];
        if (arr.length == replacement.length) {
            for (int k = 0; k < replacement.length; k++) {
                li.add(replaceChar(arr[k], replacement[k]));
                //Arrays.stream(arr).map(x -> replaceChar(x, k)).collect(Collectors.toList());
            }
            li.forEach(System.out::println);
            for (int x = 0; x < li.size(); x++) {
                if (isBalanced(li.get(x))) {
                    result[x] = 1;
                } else {
                    result[x] = 0;
                }
            }

        }
        Arrays.stream(result).forEach(System.out::println);
    }

    private static boolean isBalanced(String s) {
        boolean flag = true;
        int index = s.indexOf(">");
        for (int i = index; i < s.length(); i++) {
            if (String.valueOf(s.toCharArray()[i]).equalsIgnoreCase( "<")) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private static String replaceChar(String x, int val) {
        String result = "";
        char[] listChar = x.toCharArray();
        for (char replacementChar : listChar) {
            String si = String.valueOf(replacementChar);
            int countReplacement = 0;
            if (val != 0) {
                if (si.equalsIgnoreCase(">") && countReplacement < val) {
                    si = "<>";
                    countReplacement++;
                }
            }
            result = result + si;
        }
        return result;
    }
}
