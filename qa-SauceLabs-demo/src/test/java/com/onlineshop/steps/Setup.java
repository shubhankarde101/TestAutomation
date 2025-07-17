package com.onlineshop.steps;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Setup extends BaseStep {

    @Before
    public void preCondition() {
        initPageObject();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] src = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(src, "image/png", "screenshot");
    }

    @After
    public void postCondition(Scenario scenario) {
        driver.quit();
    }
}