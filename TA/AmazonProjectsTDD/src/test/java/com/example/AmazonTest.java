package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.example.startup.BaseTest;

public class AmazonTest extends BaseTest {
	private static final Logger LOGGER = LogManager.getLogger();

	@Test(description = "1. Verify 'About this item' functionality in Amazon home page ")
	public void validatePageTitle() throws InterruptedException {
		
		LOGGER.info("Validation started");
		dashboardpage.lnkHamburger.click();
		LOGGER.info("Hamburger menu clicked");
		dashboardpage.clickLink("TV, Appliances, Electronics");
		LOGGER.info("Clicked on 'TV, Appliances, Electronics'");
		dashboardpage.clickLink("Televisions");
		LOGGER.info("Clicked on 'Televisions'");
		dashboardpage.selectBrandSamsung();
		LOGGER.info("Samsung Brand selected");
		dashboardpage.selectPriceHighToLow();
		LOGGER.info("Selected price High to Low");
		dashboardpage.selectSecondHighest();
		LOGGER.info("Selected the second highest item");
		dashboardpage.printSectionTextsToConsole();
		LOGGER.info("Validation completed");

		
	}

	

}
