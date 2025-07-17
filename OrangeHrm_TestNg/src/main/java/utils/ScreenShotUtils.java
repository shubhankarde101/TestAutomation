package utils;

import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenShotUtils {
 private ScreenShotUtils(){

 }

 public static String getBase64Images(){
     try {
         Thread.sleep(3000);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
     return  ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BASE64);
 }

}
