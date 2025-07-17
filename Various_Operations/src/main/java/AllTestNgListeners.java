
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllTestNgListeners {

	
	
	
	@DataProvider
    public static Object[][] dataMethod() {
        return new Object[][] { { 0 }, { 1 } };
    }
 
	
	@Test(priority = 2)
	public void m1() {
		System.out.println("m1 method");
	}

	@Test(description = "test method 2 description")
	public void m2() {
		System.out.println("m2 method");
	}

	@Test(priority = 1)
	public void m3() {
		System.out.println("m3 method");
	}

	@Test(invocationCount = 2)
	public void m4() {
		System.out.println("m4 method");
	}

	@Test(enabled = false)
	public void m5() {
		System.out.println("m5 method");
	}

	@Test(dependsOnMethods = { "m1" })
	public void m6() {
		System.out.println("m6 method");
	}
	
	@Test()
	public void m7() {
		System.out.println("m7 method");
	}
	
	@Test(dataProvider = "dataMethod")
	public void m8(int parameter) {
	System.out.println("m8: test method to be called with the following parameter is " + parameter);
	}

	/*  Normal Output with Annotation Transformer listener
	 * 
	 * PASSED: m2 test method 2 description 
	 * PASSED: m4 
	 * PASSED: m4 
	 * PASSED: m3
	 * PASSED: m1 
	 * PASSED: m6
	 * 
	  */

}
