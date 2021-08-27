import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class Hookable implements IHookable {

	public void run(IHookCallBack callBack, ITestResult testResult) {
		// TODO Auto-generated method stub

		if (testResult.getName().equalsIgnoreCase("m8")) {
			System.out.println("Skip the required parameter");
		} else {
			callBack.runTestMethod(testResult);
		}

	}

}