package com.apple.phoenix.MyCode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

2.	The candidate should get familiar with https://openlibrary.org/dev/docs/api/search API call
3.	The candidate should create a working C# code that:
3.1.	Calls the API to search for any book having [Goodnight Moon] in its title, deserializes Json and
3.1.1.	Prints out the total number of books with the title matching exactly [Goodnight Moon] (case sensitive)
3.1.2.	Prints out the list of [key] of books that were published since 2000
3.2.	Calls the API to search for any book having [Goodnight Moon Base] in its title, deserializes Json and
3.2.1.	Validates whether the response matches the below expected response. If not matched, print out the difference:
4.	Include the printed answers from the above questions



public class ComplexJsonParsing {

    @Test
    public void testFetchResponse() throws IOException {

        //Pre-Processing
        Response response = given().get("https://openlibrary.org/search.json?q=Goodnight+Moon");
        response.then().log().all();
        JSONObject jo = new JSONObject(new String(response.asByteArray(), StandardCharsets.UTF_8));
        JSONArray ja = jo.getJSONArray("docs");
        int arrayLength = ja.length();
        System.out.println("The length of the array is: " + arrayLength);

        //part1
        JSONArray ja_new_title = new JSONArray();
        for (int i = 0; i < arrayLength; i++) {
            JSONObject jo_buff1 = ja.getJSONObject(i);
            if (jo_buff1.get("title").equals("Goodnight Moon"))
                ja_new_title.put(jo_buff1);
        }
        System.out.println("The length of the new array with title 'Goodnight Moon' is: " + ja_new_title.length());
        System.out.println(ja_new_title);
        System.out.println("\n");


        //part2
        JSONArray ja_new_title_year = new JSONArray();
        for (int i = 0; i < arrayLength; i++) {
            JSONObject jo_buff2 = ja.getJSONObject(i);
            if (jo_buff2.get("title").equals("Goodnight Moon")) {
                try {
                    JSONArray date = jo_buff2.getJSONArray("publish_year");
                    if (verifyPblishedDate(date))
                        ja_new_title_year.put(jo_buff2.getString("key"));
                } catch (JSONException e) {
                    System.out.println("Published date not mentioned for " + jo_buff2.getString("key"));

                }
            }
        }
        System.out.println("The length of the new array with provided title and year is: " + ja_new_title_year.length());
        System.out.println(ja_new_title_year);
        System.out.println("\n");


        //part3- Comparison with respect to expected node and print difference
        for (int i = 0; i < arrayLength; i++) {
            JSONObject jo_buff3 = ja.getJSONObject(i);
            if (jo_buff3.get("title").equals("Goodnight Moon Base")) {
                compareDiff(jo_buff3);
            }
        }
    }

    private boolean verifyPblishedDate(JSONArray dates) {
        List<String> list = dates.toList()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        return list.stream().map(x -> Double.parseDouble(x)).filter(y -> y >= 2000).findAny().isPresent();
    }

    private void compareDiff(JSONObject jo_buff3) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JSONObject jo_1 = new JSONObject();
        JSONArray ja_1 = new JSONArray();
        ja_1.put(jo_buff3);
        jo_1.put("docs",ja_1);
        JsonNode node1 = mapper.readTree(jo_1.toString());
        System.out.println("Printing the actual json file");
        System.out.println(node1.toString());


        JsonNode node2 = mapper.readTree(new File("src/main/java/com/apple/phoenix/MyCode/SampleJson/expcted.json"));
        System.out.println("Printing the expected json file after reading from a file");
        System.out.println(node2.toString());

        JsonNode diff = compareJsonNodes(node1, node2);
        System.out.println("Differences:");
        System.out.println(diff.toString());
        mapper.writeValue(new File("src/main/java/com/apple/phoenix/MyCode/SampleJson/outputdiff.json"), diff);
    }

    private static JsonNode compareJsonNodes(JsonNode actualNode1, JsonNode expectedNode2) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // If the nodes are not the same type, return the second node as the difference
        if (actualNode1.getNodeType() != expectedNode2.getNodeType()) {
            return expectedNode2;
        }
        // Compare the nodes based on their type
        switch (expectedNode2.getNodeType()) {
            case OBJECT:
                ObjectNode diff = mapper.createObjectNode();
                // Compare the properties of the two objects
                for (Iterator<String> it = expectedNode2.fieldNames(); it.hasNext(); ) {
                    String fieldName = it.next();
                    if (!actualNode1.has(fieldName)) {
                        diff.set(fieldName, expectedNode2.get(fieldName));
                    } else {
                        JsonNode childDiff = compareJsonNodes(actualNode1.get(fieldName), expectedNode2.get(fieldName));
                        diff.set(fieldName, childDiff);

                    }
                }
                return diff.size() == 0 ? mapper.createObjectNode() : diff;
            case ARRAY:
                ArrayNode arrayDiff = mapper.createArrayNode();
                //Compare the elements of the two arrays
                int iterationCount = Math.min(actualNode1.size(), expectedNode2.size());
                for (int i = 0; i < iterationCount; i++) {
                    JsonNode childDiff = compareJsonNodes(actualNode1.get(i), expectedNode2.get(i));
                    arrayDiff.add(childDiff);
                }
                return arrayDiff.size() == 0 ? mapper.createObjectNode() : arrayDiff;
            case NUMBER:
            case STRING:
            case BOOLEAN:
                return actualNode1.equals(expectedNode2) ? mapper.createObjectNode() : expectedNode2;
            case NULL:
            case MISSING:
                return expectedNode2;
            default:
                throw new IllegalArgumentException("Unknown node type: " + actualNode1.getNodeType());
        }
    }


}
