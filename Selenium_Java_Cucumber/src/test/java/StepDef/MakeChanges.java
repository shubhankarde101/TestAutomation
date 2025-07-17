package StepDef;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.pageObjects.LoginPage;
import com.automation.picoContainer.Container;
import com.automation.utilities.ActionMethods;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MakeChanges  {

	WebDriver driver = SetUp.driver;
	LoginPage loginPageobj = SetUp.loginPageobj;
	ActionMethods user = new ActionMethods();
	private Container cont;
	
	public  MakeChanges(Container cont)
	{
		this.cont = cont;
	}

	@Given("^Facebook Home Page loads successfully$")
	public void facebook_Home_Page_loads_successfully() throws Throwable {
		cont.incrementStepCount();
		// user.sync(driver, loginPageobj.LoginId);
		System.out.println("Login Page Loaded Successfully");
		Assert.assertEquals("Title not matched", "Facebook – log in or sign up", driver.getTitle().trim());
		
		
	}

	@Then("^Enter LoginId \"(.*?)\"$")
	public void enter(String loginId) throws Throwable {
		cont.incrementStepCount();
		user.type(loginPageobj.LoginId, loginId);
		
		

	}

	@Then("^Enter Password \"(.*?)\"$")
	public void enterPassword(String Password) throws Throwable {
		cont.incrementStepCount();
		user.type(loginPageobj.Password, Password);
		
		

	}

	@Then("^I will verify the \"([^\"]*)\" button$")
	public void i_will_verify_the_button(String btn_txt) throws Throwable {
		cont.incrementStepCount();
		// Write code here that turns the phrase above into concrete actions
		String btn_actual = loginPageobj.btnLogin.getAttribute("innerText").trim();
		Assert.assertEquals(btn_txt, btn_actual);
		
		//Assert.assertTrue(false);
		

	}

	@Then("^Create user account and enter the following details$")
	public void enter_the_following_details(DataTable table) throws Throwable {

		
		cont.incrementStepCount();
		user.click(loginPageobj.btnCreateAccount);		
		Wait<WebDriver> wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageobj.getDay()));
		List<List<String>> data = table.cells();
		user.selectElementFromDropDown(driver, loginPageobj.getDay(), data.get(0).get(0));
		user.selectElementFromDropDown(driver, loginPageobj.getMonth(), data.get(1).get(0));
		user.selectElementFromDropDown(driver, loginPageobj.getYear(), data.get(2).get(0));
		if (data.get(3).get(0).equalsIgnoreCase("Female")) {
			user.click(loginPageobj.Female);
		}
		

	}

}
