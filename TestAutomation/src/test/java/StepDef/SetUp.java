package StepDef;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class SetUp extends BasePage {
	
	@Before	public void setupTest() throws Exception
	{
		loadDriver();		
	}	
	
	@After
	public  void tearDown(Scenario result) throws Exception
	{		
		if(result.isFailed()){
			result.write(result.toString());
			result.write(result.getStatus());
			result.write("ScreenShot taken for failed step ");
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			result.write(message);
			result.embed(screenshot, "image/png");
		}
		result.write(message);	
		closeBrowser();
	}

}
