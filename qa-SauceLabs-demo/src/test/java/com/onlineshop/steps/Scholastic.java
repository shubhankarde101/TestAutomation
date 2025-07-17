package com.onlineshop.steps;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Scholastic {

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS );
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        try {
        driver.get("https://stage-slz01.scholasticlearningzone.com/resources/dp-int/dist/#/login3/student/AUSWHG9");
        driver.manage().window().maximize();
        driver.findElement(By.name("username")).sendKeys("Student21");
        System.out.println("User Id entered");
        driver.findElement(By.name("password")).sendKeys("welcome1");
        System.out.println("Password entered");
        driver.findElement(By.xpath("//button[contains(text(), 'LOGIN')]")).click();
        System.out.println("Login button clicked");
        driver.findElement(By.xpath("(//div[normalize-space() = 'LITERACY PRO NEXTGEN'])[1]")).click();
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("Main Window Handle: " + mainWindowHandle);
        hardWait(5);
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    System.out.println("Switched to window with handle: " + handle);
                    System.out.println("New window title: " + driver.getTitle());
                }
        }
        WebElement read = driver.findElement(By.xpath(".//*[text()='Assigned to Me']//..//..//button[text()='Read']"));
        String action = read.getText().trim();
        if (action.equals("Read")) {
                System.out.println("The action selected is:"+action);
                jsExecutor.executeScript("arguments[0].click();", read);
                hardWait(5);
                LocalTime startTime = LocalTime.now();
                System.out.println("Start time: " + startTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
                WebElement nextBtn = driver.findElement(By.cssSelector("div.next-button button"));
                WebElement prevBtn = driver.findElement(By.cssSelector("div.prev-button button"));
                int count = Integer.parseInt(driver.findElement(By.cssSelector("div.jump-to-page span")).getText().replace("/", "").trim());
                for (int i = 0; i < count; i++) {
                    if(!Objects.requireNonNull(prevBtn.getAttribute("class")).equalsIgnoreCase("disabled")) {
                        jsExecutor.executeScript("arguments[0].click();", prevBtn);
                        hardWait(3);
                    }
                }
                for (int i = 0; i < 5; i++) {
                    jsExecutor.executeScript("arguments[0].click();", nextBtn);
                    hardWait(3);
                }
                driver.findElement(By.cssSelector("div.modal-close-btn")).click();
                LocalTime endTime = LocalTime.now();
                Duration duration = Duration.between(startTime, endTime);
                long minutes = duration.toMinutes();
                long seconds = duration.getSeconds() % 60;
                System.out.println("Total time spent reading: " + minutes + " minutes and " + seconds + " seconds");
            }

        driver.quit();
    }catch (Exception e)
    {
        e.printStackTrace();
        driver.quit();
    }

    }

    public static void hardWait(int second)
    {
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
