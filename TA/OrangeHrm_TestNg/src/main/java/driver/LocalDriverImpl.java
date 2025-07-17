package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.PropertyUtils;

import java.util.concurrent.TimeUnit;

public class LocalDriverImpl{
    @SneakyThrows
    public WebDriver getDriver() {
        WebDriverManager.chromiumdriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(PropertyUtils
                .getPropertyValue("URL"));
        return driver;
    }
}
