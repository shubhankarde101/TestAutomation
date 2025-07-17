package com.apple.phoenix.MyCode;

import java.util.*;
import java.util.stream.Collectors;

public class DistributeStringsIntoSets {
    public static void main(String[] args) {
        List<Set<String>> list = new ArrayList<>();
        String[] strings1 = {"vanilla", "apple", "banana", "cherry", "peach", "banana"};
        String[] strings2 = {"apple", "vanilla", "apple", "vanilla"};
        String[] strings3 = {"apple","peach","peach","vanilla"};
        String[] strings4 = {"apple"};
        addInSet(strings4, list);
        int totalPrice = 0;int unitPrice=4;
        for(Set s:list)
        {
            switch(s.size()) {
                case 5:
                    totalPrice= totalPrice+s.size()*unitPrice*3/5;
                    break;
                case 4:
                    totalPrice= totalPrice+s.size()*unitPrice*7/10;
                    break;
                case 3:
                    totalPrice= totalPrice+s.size()*unitPrice*4/5;
                    break;
                case 2:
                    totalPrice= totalPrice+s.size()*unitPrice*9/5;
                    break;
                default:
                    totalPrice= totalPrice+s.size()*unitPrice;
            }
        }
        System.out.println("The list of sets are" + list);
        System.out.println("The total price is: "+totalPrice);
    }

    public static void addInSet(String[] arr, List<Set<String>> li) {
        Set<String> currentSet = new HashSet<>();
        if (arr.length == 1) {
            currentSet.add(arr[0]);
            li.add(currentSet);
        } else {
            for (String str : arr) {
                if (currentSet.size() > 4) {
                    break;
                }
                currentSet.add(str);
            }
            String[] remainingStrings = calculateRemainingString(arr, currentSet);
            if (remainingStrings != null) {
                li.add(currentSet);
                addInSet(remainingStrings, li);
            }
        }
    }

    public static String[] calculateRemainingString(String[] arr, Set<String> set) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String str : arr) {
            frequencyMap.put(str, frequencyMap.getOrDefault(str, 0) + 1);
        }
        for (String str1 : set) {
            if (frequencyMap.keySet().contains(str1)) {
                frequencyMap.put(str1, frequencyMap.get(str1) - 1);
            }
        }
        List<String> lit = new ArrayList<>();
        for (String str2 : frequencyMap.keySet()) {
            if (frequencyMap.get(str2) != 0) {
                int l = frequencyMap.get(str2);
                for (int i = 0; i < l; i++) {
                    lit.add(str2);
                }
            }
        }
        if (frequencyMap.values().stream().allMatch(x -> x == 0))
            return null;
        else
            return lit.toArray(new String[0]);
    }
}
