package StepDef;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.automation.pageObjects.LoginPage;
import com.automation.utilities.ActionMethods;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

public class MakeChanges extends BasePage {

	WebDriver driver = SetUp.driver;
	LoginPage loginPageobj = SetUp.loginPageobj;
	ActionMethods user = new ActionMethods();

	@Given("^Facebook Home Page loads successfully$")
	public void facebook_Home_Page_loads_successfully() throws Throwable {
		// user.sync(driver, loginPageobj.LoginId);
		System.out.println("Login Page Loaded Successfully");

	}

	@Then("^Enter LoginId \"(.*?)\"$")
	public void enter(String loginId) throws Throwable {
		user.type(loginPageobj.LoginId, loginId);

	}

	@Then("^Enter Password \"(.*?)\"$")
	public void enterPassword(String Password) throws Throwable {
		user.type(loginPageobj.Password, Password);

	}

	@Then("^Enter the following details$")
	public void enter_the_following_details(DataTable table) throws Throwable {

		List<List<String>> data = table.raw();
		user.selectElementFromDropDown(driver, loginPageobj.getDay(), data.get(1).get(1));
		user.selectElementFromDropDown(driver, loginPageobj.getMonth(), data.get(2).get(1));
		user.selectElementFromDropDown(driver, loginPageobj.getYear(), data.get(3).get(1));
		if (data.get(4).get(1).equalsIgnoreCase("Female")) {
			user.click(loginPageobj.Female);
		}

	}

	@SuppressWarnings("deprecation")
	@Then("^I will verify the \"([^\"]*)\" button$")
	public void i_will_verify_the_button(String btn_txt) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String btn_actual = loginPageobj.btnLogin.getAttribute("innerText").trim();
		Assert.assertEquals(btn_txt, btn_actual);

	}

}
