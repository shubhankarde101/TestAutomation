package StepDef;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class MakeChanges extends BasePage {

	@Given("^Crypto Page loads successfully$")
	public void facebook_Home_Page_loads_successfully() throws Throwable {

		System.out.println("Login Page Loaded Successfully");
		Assert.assertEquals(driver.getTitle(), "Cryptocurrency Market Capitalizations | CoinMarketCap");

	}

	@Then("^Verify column \"([^\"]*)\"$")
	public void verifyColumn(String col) throws Throwable {

		WebElement ele = testPageobj.getCoumnName(driver, col);
		Assert.assertEquals(ele.getText(), col);

	}

	@Then("^Verify currency \"([^\"]*)\" is available$")
	public void verifyCurrencyName(String curr) throws Throwable {

		List<WebElement> ele = testPageobj.getCurrencyName("currency");
		String result = ele.stream().filter(element -> element.getText().equalsIgnoreCase(curr)).findAny().get()
				.getText();
		System.out.println("Actual result: " + result);
		Assert.assertEquals(result, curr);

	}

	@Then("^Verify currency \"([^\"]*)\" for \"([^\"]*)\" change with \"([^\"]*)\"$")
	public void verifyCurrencyData(String currname, String change, String param, DataTable table) throws Throwable {

		
		List<WebElement> ele = testPageobj.getCurrencyData(currname, change, param);
		ele.forEach(e -> System.out.println(e.getAttribute("innerText")));
		List<List<String>> data = table.raw();
		SoftAssert softAssert = new SoftAssert();
		if (ele.size() == data.size())
			for (int i = 0; i < ele.size(); i++) {
				softAssert.assertTrue(ele.get(i).getAttribute("innerText").trim().replace(",", "").trim().contains(data.get(i).get(0)),"Validation failed for: "+data.get(i).get(0));
			}
		softAssert.assertAll();
	}
	
	@Then("^click on \"([^\"]*)\"$")
	public void verifyNavBar(String val) throws Throwable {

		WebElement ele = testPageobj.getNavBar(val);		
		user.click(ele);
		Assert.assertTrue(testPageobj.getNavDropdown(val).isDisplayed());	

	}
	
	@And("^type something on the search box$")
	public void verifySrchBar() throws Throwable {

		WebElement ele = testPageobj.getSearchInput();
		user.type(ele, "Testing");

	}
	@Then("^Change the currency value to \"([^\"]*)\"$")
	public void changeCurrValue(String currval) throws Throwable {

		WebElement ele = testPageobj.getCurrSwitchBtn();		
		user.click(ele);
		List<WebElement> allCuurencies = testPageobj.getCurrSwitchDropDown();
		user.click(allCuurencies.stream().filter(element -> element.getText().equalsIgnoreCase(currval)).findAny().get());
		//Thread.sleep(3000);	

	}
	
	@And("^Verify 'Markt Capital' sum value$")
	public void verifyMarketCapitalSum() throws Throwable {

		List<WebElement> ele = testPageobj.getCurrencyName("market-cap");
		long totalSum = 0;		
		for(WebElement e:ele)
		{
			String buff_Val = e.getAttribute("innerText").trim().replace("$", "").replace(",", "").trim();
			//System.out.println(buff_Val);
			totalSum = totalSum+Long.parseLong(buff_Val);			
		}
		System.out.println("Total sum is: "+totalSum);
		Assert.assertTrue(totalSum>2.00000000000e+11);

	}
	
	


}
