package com.affle.iOS.screen;

import com.affle.iOS.Action.ActionPage;
import com.affle.iOS.driver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SplashScreen extends ActionPage {






    public SplashScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(
                DriverManager.getWebDriver()), this);
    }



}
