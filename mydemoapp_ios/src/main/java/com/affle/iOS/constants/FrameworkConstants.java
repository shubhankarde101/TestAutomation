package com.affle.iOS.constants;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class FrameworkConstants {
    private FrameworkConstants() {
    }

    private static FrameworkConstants frameworkConstants;

    public static synchronized FrameworkConstants getInstance(){
        if (Objects.isNull(frameworkConstants)){
            frameworkConstants = new FrameworkConstants();
        }
        return frameworkConstants;
    }
    private final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
    private final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";

    String APPPATH = RESOURCESPATH + "/iPAFiles/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip";

    String APPPATH1 = RESOURCESPATH + "/iPAFiles/Patient_App_07_Mar23.ipa";

    String DOCAPPPATH = RESOURCESPATH + "/iPAFiles/Doctor App 18 Oct.ipa";
    @Getter
    private static final int EXPLICITLYWAIT = 30;
    @Getter
    private static final String emailID = "";
    @Getter
    private static final String password ="";
}
