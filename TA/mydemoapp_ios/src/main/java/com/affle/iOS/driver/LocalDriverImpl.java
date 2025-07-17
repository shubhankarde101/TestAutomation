package com.affle.iOS.driver;

import com.affle.iOS.constants.FrameworkConstants;
import com.affle.iOS.utils.PropertyUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static io.appium.java_client.remote.IOSMobileCapabilityType.USE_PREBUILT_WDA;


public class LocalDriverImpl implements IDriver{
    @SneakyThrows
    @Override
    public IOSDriver<IOSElement> getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.APP, FrameworkConstants.getInstance().getAPPPATH());
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"16.2");
        //desiredCapabilities.setCapability(MobileCapabilityType.UDID, PropertyUtils.getPropertyValue("udid"));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,PropertyUtils.getPropertyValue("DevicesName"));
        //desiredCapabilities.setCapability(USE_PREBUILT_WDA, true);
        //desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,"true");
        //desiredCapabilities.setCapability("autoAcceptAlerts", "true");

        URL url = new URL("http://127.0.0.1:4723/");
         return new IOSDriver<>
                (url, desiredCapabilities);
    }
}
