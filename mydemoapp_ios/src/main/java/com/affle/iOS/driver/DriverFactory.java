package com.affle.iOS.driver;

import com.affle.iOS.enums.Modes;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DriverFactory {
    private DriverFactory(){}
    private static final Supplier<IOSDriver> LOCAL = ()-> new LocalDriverImpl().getDriver();
    private static final Supplier<WebDriver> BS = ()-> new BrowserStackImpl().getDriver();
    private static final Supplier<WebDriver> SAUCELABS = ()-> new SauceLabsImpl().getDriver();


    private static final Map<Modes, Supplier<IOSDriver>> MAP = new EnumMap<>(Modes.class);
    static {
        MAP.put(Modes.LOCAL,LOCAL);
//        MAP.put(Modes.BS,BS);
//        MAP.put(Modes.SAUCELABS,SAUCELABS);
    }
    public static IOSDriver getDriver(Modes modes){
        return MAP.get(modes).get();
    }
}
