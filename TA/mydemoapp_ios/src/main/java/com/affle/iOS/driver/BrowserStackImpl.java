package com.affle.iOS.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BrowserStackImpl implements IDriver{

    @SneakyThrows
    @Override
    public WebDriver getDriver()  {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        /*
        We need to browser stack account

        desiredCapabilities.setCapability(MobileCapabilityType.APP,"");
        desiredCapabilities.setCapability("", "");
        desiredCapabilities.setCapability("", "");
        desiredCapabilities.setCapability("", "9.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"");
        desiredCapabilities.setCapability("", "");
        desiredCapabilities.setCapability("", "");

         */
        return new AndroidDriver<AndroidElement>
                (new URL("http:/hub.browserstack.com/wd/hub"),desiredCapabilities);

    }
}
