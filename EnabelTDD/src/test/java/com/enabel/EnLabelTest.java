package com.enabel;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.enabel.startup.BaseTest;
import com.enabel.utils.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class EnLabelTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test(description = "Get the list of subcategories from each menu. " +
            "Click on each sub-categories and validate that the page is loaded successfully.")
    public void VerifyListAndSublist() {
        LOGGER.info("----------------------Start----------------------------------");
        LOGGER.info("Validation started");
        ExtentTestManager.getTest().pass("Validation started").addScreenCaptureFromBase64String(SeleniumUtils.takeScreenshot());
        ;
        enLabelPage.categoryAndSubCategoryPages("About Us");
        LOGGER.info("Category 'About Us' and it's Sub-categories are validated successfully");
        ExtentTestManager.getTest().pass("Category 'About Us' and it's Sub-categories are validated successfully").addScreenCaptureFromBase64String(SeleniumUtils.takeScreenshot());
        enLabelPage.categoryAndSubCategoryPages("Media");
        LOGGER.info("Category 'Media' and it's Sub-categories are validated successfully");
        ExtentTestManager.getTest().pass("Category 'Media' and it's Sub-categories are validated successfully").addScreenCaptureFromBase64String(SeleniumUtils.takeScreenshot());
        ;
        enLabelPage.categoryAndSubCategoryPages("Platform");
        LOGGER.info("Category 'Platform' and it's Sub-categories are validated successfully");
        ExtentTestManager.getTest().pass("Category 'Platform' and it's Sub-categories are validated successfully").addScreenCaptureFromBase64String(SeleniumUtils.takeScreenshot());
        ;
        enLabelPage.categoryAndSubCategoryPages("Services");
        LOGGER.info("Category 'Services' and it's Sub-categories are validated successfully");
        ExtentTestManager.getTest().pass("Category 'Services' and it's Sub-categories are validated successfully").addScreenCaptureFromBase64String(SeleniumUtils.takeScreenshot());
        ;
        enLabelPage.categoryAndSubCategoryPages("Compliance");
        LOGGER.info("Category 'Compliance' and it's Sub-categories are validated successfully");
        ExtentTestManager.getTest().pass("Category 'Compliance' and it's Sub-categories are validated successfully").addScreenCaptureFromBase64String(SeleniumUtils.takeScreenshot());
        ;
        enLabelPage.categoryAndSubCategoryPages("Partners");
        LOGGER.info("Category 'Partners' and it's Sub-categories are validated successfully");
        ExtentTestManager.getTest().pass("Category 'Partners' and it's Sub-categories are validated successfully")
                .addScreenCaptureFromBase64String(SeleniumUtils.takeScreenshot());
        ;
        enLabelPage.categoryAndSubCategoryPages("Resources");
        LOGGER.info("Category 'Resources' and it's Sub-categories are validated successfully");
        ExtentTestManager.getTest().pass("Category 'Resources' and it's Sub-categories are validated successfully").addScreenCaptureFromBase64String(SeleniumUtils.takeScreenshot());
        ;
        LOGGER.info("----------------------End----------------------------------");
    }
}
