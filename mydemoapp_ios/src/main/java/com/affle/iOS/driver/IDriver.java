package com.affle.iOS.driver;


import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface IDriver {
    WebDriver getDriver() throws MalformedURLException;
}
