package com.apple.phoenix.MyCode;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class SeleniumSvgHandling {

    public static void main(String[] args) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://petdiseasealerts.org/forecast-map");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement frame_default = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//iframe[contains(@id,'map-instance')]")));
        driver.switchTo().frame(frame_default);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.body.style.zoom='80%'");
        // js.executeScript("arguments[0].style.display = 'block';", sourceCountry);
        //js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true}));", sourceCountry);
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        ts.getScreenshotAs(OutputType.FILE);
        Actions builder = new Actions(driver);
        List<String> countries = Arrays.asList("California", "Maryland", "Florida", "New York");
        for (String strCountry : countries) {
            Thread.sleep(3000);
            String countryXpath = ".//*[name()='svg']//*[local-name()='g' and @id='regions']//*[local-name()='g' and @id='##']".replace("##", strCountry.toLowerCase().replace(" ","-"));
            WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(countryXpath)));
            js.executeScript("arguments[0].scrollIntoView();", country);
            if (strCountry.equalsIgnoreCase("Florida")) {
                int y = country.getSize().getHeight() / 2 - country.getSize().getHeight() + 10;
                int x = country.getSize().getWidth() / 2 - country.getSize().getWidth() + 10;
                builder.moveToElement(country, x, y).click().perform();
            } else {
                builder.moveToElement(country).click().perform();
            }
            Thread.sleep(2000);
            WebElement validateCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[text()='" + strCountry + "']")));
            Assert.assertTrue(validateCountry.isDisplayed());
            Thread.sleep(5000);
            driver.navigate().refresh();
            driver.get("https://petdiseasealerts.org/forecast-map/#/");
            WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//iframe[contains(@id,'map-instance')]")));
            driver.switchTo().frame(frame);
        }
        driver.quit();
    }
}


