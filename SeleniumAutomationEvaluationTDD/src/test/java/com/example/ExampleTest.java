package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.startup.BaseTest;
import com.example.utils.WaitElement;

public class ExampleTest extends BaseTest {
	private static final Logger LOGGER = LogManager.getLogger();

	
	  @Test(description = "Test1") public void Test1() throws InterruptedException
	  {
	  
	  WaitElement.waitVisibilityOfElement(driver, homePage.inputEmail);
	  Assert.assertTrue(homePage.inputEmail.isDisplayed(), "Email not present");
	  Assert.assertTrue(homePage.inputPassword.isDisplayed(),
	  "Password not present"); Assert.assertTrue(homePage.btnLogin.isDisplayed(),
	  "Login button not present"); homePage.inputEmail.sendKeys("test@abc.com");
	  homePage.inputPassword.sendKeys("test@1234"); homePage.btnLogin.click();
	  LOGGER.info("Validation Successful");
	  
	  }
	  
	  @Test(description = "Test2") public void Test2() throws InterruptedException
	  { WaitElement.waitVisibilityOfElement(driver, homePage.inputEmail);
	  Assert.assertTrue(homePage.listItems.size() == 3,
	  "List items have either more or less than 3 values"); String actualValue =
	  homePage.listItems.get(1).getText().trim();
	  Assert.assertTrue(actualValue.contains("List Item 2"), "Actual value " +
	  actualValue + " not matched for List Item 2");
	  Assert.assertTrue(actualValue.contains("6"), "Actual value " + actualValue +
	  " not matched for value 6");
	  LOGGER.info("Validation Successful");
	  
	  }
	  
	  @Test(description = "Test3") public void Test3() throws InterruptedException
	  { WaitElement.waitVisibilityOfElement(driver, homePage.inputEmail);
	  Assert.assertEquals(homePage.optionDefault.getText().trim(), "Option 1",
	  "Option 1 is not default"); homePage.optionDefault.click();
	  homePage.listOptions.get(2).click();
	  Assert.assertEquals(homePage.optionDefault.getText().trim(), "Option 3",
	  "Option 1 is not default");
	  LOGGER.info("Validation Successful");
	  
	  }
	  
	  @Test(description = "Test4") public void Test4() throws InterruptedException
	  { WaitElement.waitVisibilityOfElement(driver, homePage.inputEmail);
	  Assert.assertTrue(homePage.listButtons.get(0).isEnabled(),
	  "First button not enabled");
	  Assert.assertTrue(!homePage.listButtons.get(1).isEnabled(),
	  "Second button not disabled");
	  LOGGER.info("Validation Successful");
	  
	  }
	  
	  @Test(description = "Test5") public void Test5() throws InterruptedException
	  { WaitElement.waitVisibilityOfElement(driver, homePage.btnFive);
	  homePage.btnFive.click();
	  LOGGER.info("Alert message is: "+homePage.txtAlertMessage.getText().trim());
	  Assert.assertEquals(homePage.txtAlertMessage.getText().trim(),
	  "You clicked a button!", "Success message not appeared");	  
	  Assert.assertTrue(!homePage.btnFive.isEnabled(), "button not disabled"); 
	  LOGGER.info("Validation Successful");
	  }
	 

	@Test(description = "Test6")
	public void Test6() throws InterruptedException {

		WaitElement.waitVisibilityOfElement(driver, homePage.inputEmail);
		String actualValue = homePage.getValueFromCell(2, 2);
		LOGGER.info("Actual value: "+actualValue);
		Assert.assertEquals(actualValue, "Ventosanzap", "Value Ventosanzap not matched");
		LOGGER.info("Validation Successful");
	}

}
