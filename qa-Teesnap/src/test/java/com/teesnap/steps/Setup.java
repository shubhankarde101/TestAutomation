package com.teesnap.steps;


import com.teesnap.driver.DriverManager;
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
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getWebDriver();
        byte[] src = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(src, "image/png", "screenshot");
    }

    @After
    public void postCondition(Scenario scenario) {
        DriverManager.getWebDriver().quit();
    }
}