package com.amazon;

import com.amazon.page.DashboaedPage;
import com.amazon.utils.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.startup.BaseTest;

public class AmazonHeadPhoneItemTest extends BaseTest {
	private static final Logger LOGGER = LogManager.getLogger();

	@DataProvider(name = "data-provider")
	public Object[][] dpMethod(){
		return new Object[][] {{"headphones", "Wireless" , 5}};
	}

	@Test(description = "1.Verify user is able to add wireless head phone into Cart and proceed to checkout", dataProvider = "data-provider")
	public void testVerifyHeadPhoneSearch(String item, String connectivity, int nthItem){
		LOGGER.info("----------------------Start----------------------------------");
		LOGGER.info("Validation started");
		System.out.println(SeleniumUtils.getValueByKey("name"));
		dashBoardpage.searchItem(item);
		LOGGER.info("Searched item head phone");
		dashBoardpage.selectConnectivityType(connectivity);
		LOGGER.info("Wireless connectivity selected");
		dashBoardpage.selectNthItem(nthItem);
		LOGGER.info("Expected product title is: " + DashboaedPage.productTitle);
		LOGGER.info("Expected product price is: " + DashboaedPage.productPrice);
		LOGGER.info("Expected product rating is: " + DashboaedPage.productRating);
		LOGGER.info("Selected the 5th item from the search result");
		productPage.addToCart();
		LOGGER.info("Identified product added to the cart");
		cartPage.verifyCartAndCheckout();
		LOGGER.info("Item verified and checkout in progress");
		LOGGER.info("----------------------End----------------------------------");
	}
}
