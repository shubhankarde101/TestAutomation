package com.patient.screen;

import com.affle.iOS.Action.ActionPage;
import com.affle.iOS.driver.DriverManager;
import com.affle.iOS.enums.LogType;
import com.affle.iOS.enums.WaitStrategy;
import com.affle.iOS.factory.ExplicitWaitFactory;
import com.affle.iOS.reports.FrameWorkLogger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderToCashFlowScreen extends ActionPage {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='store item']//XCUIElementTypeStaticText[@name='store item text']")
    List<MobileElement> itemLabels;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='store item']//XCUIElementTypeStaticText[@name='store item price']")
    List<MobileElement> itemPrices;



    public OrderToCashFlowScreen() {
        Duration d = Duration.ofSeconds(10L);
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getWebDriver(), d), this);
    }

    public boolean captureProductDetails(String label, String price) {
        FrameWorkLogger.log(LogType.INFO, "Capturing product details");
        List<String> labels = itemLabels.stream().map(x->x.getAttribute("label")).collect(Collectors.toList());
        labels.stream().forEach(x->System.out.println(x));
        List<String> prices = itemPrices.stream().map(x->x.getAttribute("label")).collect(Collectors.toList());
        prices.stream().forEach(x->System.out.println(x));
        Map<String, String> hashMap = IntStream.range(0, Math.min(labels.size(), prices.size()))
                .boxed()
                .collect(Collectors.toMap(labels::get, prices::get, (oldValue, newValue) -> newValue, () -> new HashMap<>()));
        FrameWorkLogger.log(LogType.INFO, "Printing the product details");
        FrameWorkLogger.log(LogType.INFO, hashMap.toString());
        return hashMap.keySet().stream().noneMatch(x->x.equalsIgnoreCase(label)&&hashMap.get(x).equalsIgnoreCase(price));
    }

}
