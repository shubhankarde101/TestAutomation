package com.mapViewer;

import com.mapViewer.startup.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MapperTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test(description = "Verify user is able to Zoom In")
    @Parameters("country")
    public void verifyZoomIn(String country) {
        LOGGER.info("----------------------Start----------------------------------");
        LOGGER.info("Validation started");
        mapperPage.findAddress(country);
        String actualCountry = mapperPage.verifyAddress();
        Assert.assertEquals(actualCountry,country,"Address not matching");
        LOGGER.info("----------------------End----------------------------------");
    }


    @Test(description = "Verify user is able to Zoom Out")
    @Parameters("country")
    public void verifyZoomOut(String country) {
        LOGGER.info("----------------------Start----------------------------------");
        LOGGER.info("Validation started");
        mapperPage.findAddress(country);
        mapperPage.zoomFunctionality();
        String actualCountry = mapperPage.verifyAddress();
        Assert.assertEquals(actualCountry,country,"Address not matching");
        LOGGER.info("----------------------End----------------------------------");
    }
}
