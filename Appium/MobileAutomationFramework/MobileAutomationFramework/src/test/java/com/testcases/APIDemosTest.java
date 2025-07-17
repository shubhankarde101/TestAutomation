package com.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.driver.Driver;
import com.pages.APIDemoHomePage;
import com.pages.APIDemoViewsPage;
import com.pages.HomePageUsingFindBy;
import com.pages.Preferences;

public class APIDemosTest extends BaseTest {

	APIDemosTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	APIDemoHomePage hpage;
	APIDemoViewsPage viewpage;

	// validateTapAndScrolling

	@Test
	public void testAPIDemo(Hashtable<String, String> data)
			throws NumberFormatException, IOException, InterruptedException {

		Driver.initialize(data.get("nature"), data.get("deviceName"), data.get("udid"),
				Integer.parseInt(data.get("port")));
		hpage = new APIDemoHomePage();
		viewpage = hpage.clickview();
		viewpage.scrollAndClickMenu("Expandable Lists");
		System.out.println("----------------Test1--------------------------");

	}

	@Test
	public void testAPIScroll(Hashtable<String, String> data)
			throws NumberFormatException, IOException, InterruptedException {

		Driver.initialize(data.get("nature"), data.get("deviceName"), data.get("udid"),
				Integer.parseInt(data.get("port")));
		hpage = new APIDemoHomePage();
		viewpage = hpage.clickview();
		viewpage.scrollAndClickMenu("WebView");
		System.out.println("----------------Test2--------------------------");
	}

	// using property file
	@Test(priority = 1,description = "Verify 'preference' functionality in apidemo")
	public void testButtonClick() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		Driver.initialize("Android", pf.getValue("deviceName"), pf.getValue("udid"),
				Integer.parseInt(pf.getValue("port")));
		HomePageUsingFindBy h = new HomePageUsingFindBy(Driver.getDriver());
		h.Preferences.click();
		Preferences p = new Preferences(Driver.getDriver());
		p.dependencies.click();
		Driver.getDriver().findElement(By.id("android:id/checkbox")).click();
		Driver.getDriver().findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		Driver.getDriver().findElement(By.className("android.widget.EditText")).sendKeys("App Testing");
		p.buttons.get(1).click();
		System.out.println("----------------Test3--------------------------");
	}

}
