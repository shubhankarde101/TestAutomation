package com.affle.iOS.utils;

import com.affle.iOS.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenShotUtils {
 private ScreenShotUtils(){

 }

 public static String getBase64Images(){
    return  ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BASE64);
 }

}
