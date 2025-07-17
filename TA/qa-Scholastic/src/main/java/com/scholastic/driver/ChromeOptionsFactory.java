package com.scholastic.driver;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

class ChromeOptionsFactory {

    ChromeOptions build(){
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("start-maximized");
        optionsChrome.addArguments("lang=pt-BR");
        optionsChrome.setCapability(ChromeOptions.CAPABILITY, Arrays.asList("--ignore-certificate-errors,--web-security=false,--ssl-protocol=any,--ignore-ssl-errors=true"));
        optionsChrome.setAcceptInsecureCerts(true);
        return optionsChrome;
    }
}
