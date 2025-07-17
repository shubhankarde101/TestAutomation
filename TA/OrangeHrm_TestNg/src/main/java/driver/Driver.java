package driver;

import enums.Modes;
import org.openqa.selenium.WebDriver;
import utils.PropertyUtils;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static driver.DriverManager.*;



public final class Driver {
    private Driver() {
    }

    public static void initDriver() {
        if (isNull(getWebDriver())) {
            WebDriver driver = DriverFactory.getDriver(Modes.valueOf(PropertyUtils
                                    .getPropertyValue("mode")
                                    .toUpperCase()));
            setWebDriver(driver);
        }
    }

    public static void quitDriver() {
        if (nonNull(getWebDriver())) {
            getWebDriver().quit();
            unLoadWebDriver();
        }
    }
}
