package com.amazon;

import com.amazon.startup.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class AmazonOtherTests extends BaseTest {
	private static final Logger LOGGER = LogManager.getLogger();

	@Test(description = "1.Verify item 'Samsung' added to cart, checkout item and process payment")
	public void testVerifySamsungItemAddedToCart(){
		LOGGER.info("Validation started");
		dashBoardpage.clickOnHamburgerMenu();
		LOGGER.info("Hamburger menu clicked");
		dashBoardpage.clickLink("Mobiles, Computers");
		LOGGER.info("Clicked on 'Mobiles, Computers");
		dashBoardpage.clickLink("All Mobile Phones");
		LOGGER.info("Clicked on 'All Mobile Phones'");
		dashBoardpage.selectBrand("Samsung");
		LOGGER.info("Samsung Brand selected");
		dashBoardpage.selectPriceHighToLow();
		LOGGER.info("Selected price High to Low");
		dashBoardpage.selectNthItem(1);
		LOGGER.info("Selected the second highest item");
		dashBoardpage.printSectionTextsToConsole();
		LOGGER.info("Validation completed");
		productPage.addToCart();
		LOGGER.info("Checkout and payment processed");
		LOGGER.info("Validation completed");
	}


}
