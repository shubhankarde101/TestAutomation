package StepDef;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class SetUp extends BasePage {
	
	@Before	
	public void setupTest() throws Exception
	{
		
		initializePageObjects();
		
		
	}	
	
	@After
	public  void tearDown(Scenario result) throws Exception
	{		
		if(result.isFailed()){
			
			
			result.write(result.toString());
			result.write(result.getStatus());
			result.write("ScreenShot taken for failed step ");						
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);			
			result.embed(screenshot, "image/png");
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);	
			System.out.println( "Scenario failed, hence raising bug in JIRA tools");			
			message = bug.raiseBug(result.toString(),result.getName(), props.readPropertiesFile("UserId"),file);
			result.write("\n\n"+"..Bug Raised for the Failed Scenario. Refer the JIRA link for more details: "+"\n\n");
			result.write(message);
			
		}		
		
	}

}
