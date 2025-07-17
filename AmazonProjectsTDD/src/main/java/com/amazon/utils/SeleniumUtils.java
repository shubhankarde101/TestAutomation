package com.amazon.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.logging.log4j.core.util.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class SeleniumUtils {

    public static void clickUsingJs(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void actionClick(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.click(element).build().perform();
    }

    public static void mouseHover(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static Object getValueByKey(String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File("src/test/resources/Data.json"));
            return  findValueByKey(rootNode, key);
        } catch (IOException e) {
            return null;
        }
    }

    private static Object findValueByKey(JsonNode node, String key) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if (field.getKey().equals(key)) {
                    return field.getValue();
                }
                Object value = findValueByKey(field.getValue(), key);
                if (value != null) {
                    return value;
                }
            }
        } else if (node.isArray()) {
            for (JsonNode arrayItem : node) {
                Object value = findValueByKey(arrayItem, key);
                if (value != null) {
                    return value;
                }
            }
        }
        return null;
    }




}
