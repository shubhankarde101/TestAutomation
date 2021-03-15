package StepDef;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.automation.picoContainer.Container;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestStep;

public class SetUp extends BasePage {

	// Initiating Pico-Container dependency injection
	private Container cont;
	
	 public SetUp(Container cont)
	{
		 this.cont = cont;
	}
	
	@Before
	public void setupTest() throws Exception {
		loadDriver();
		cont.setStepCount(0);	
		
	}
	
	@After
	public void tearDown(Scenario result) throws Exception {	
		
		
		if (result.isFailed()) {
			result.log(result.toString());
			result.log(result.getStatus().toString());

			System.out.println("--------------------Scenario Failed------------------");
			Field f = result.getClass().getDeclaredField("delegate");			
			
			f.setAccessible(true);
		    TestCaseState tcs = (TestCaseState) f.get(result);

		    Field f2 = tcs.getClass().getDeclaredField("testCase");
		    f2.setAccessible(true);
		    TestCase r = (TestCase) f2.get(tcs);
		    List<TestStep> stepDefs = r.getTestSteps();	   
	               
		    System.out.println("------Scenario Name: "+result.getName());
		    PickleStepTestStep pts = (PickleStepTestStep) stepDefs.get(cont.getStepCount());
			System.out.println("------Scenario step failed: "+pts.getStep().getText());

			result.log("ScreenShot taken for failed step ");
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			result.log(message);
			result.attach(screenshot, "image/png","");
			 
		}
		result.log(message);
		closeBrowser();
		
	}

}
