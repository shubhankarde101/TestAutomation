package utils;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String[] input = {"Doc", "Spy", "Doc", "sub", "Cart", "Spy", "Doc"};
        String[] output = updateDuplicates(input);

        for (String str : output) {
            System.out.println(str);
        }
    }

    public static String[] updateDuplicates(String[] input) {
        Map<String, Integer> stringCountMap = new HashMap<>();
        String[] output = new String[input.length];

        for (int i = 0; i < input.length; i++) {
            String str = input[i];

            if (stringCountMap.containsKey(str)) {
                int count = stringCountMap.get(str);
                output[i] = str + count;
                count++;
                stringCountMap.put(str, count);

            } else {
                stringCountMap.put(str, 1);
                output[i] = str;
            }
        }
        return output;
    }
}

