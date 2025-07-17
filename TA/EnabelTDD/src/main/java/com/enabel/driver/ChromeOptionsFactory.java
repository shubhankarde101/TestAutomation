package com.enabel.driver;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

class ChromeOptionsFactory {

    ChromeOptions build(){
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("start-maximized");
        //optionsChrome.addArguments("--incognito");
        optionsChrome.setAcceptInsecureCerts(true);
        HashMap<String,Object> map = new HashMap<>();
        map.put("images",2);
        HashMap<String,Object> prefsMap = new HashMap<>();
        prefsMap.put("profile.default_content_setting_values",map);
        optionsChrome.setExperimentalOption("prefs",prefsMap);
        return optionsChrome;
    }
}
