package com.apple.phoenix.MyCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PractiseTest {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hello", "World");
        List<String> letters = words.stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .collect(Collectors.toList());
        System.out.println(letters);


    }

}




