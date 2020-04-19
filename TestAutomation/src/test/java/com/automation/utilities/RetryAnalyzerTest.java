package com.automation.utilities;
import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyzerTest extends ActionMethods{
	
	@Test(priority=1)
	public void Test1() throws AWTException, InterruptedException
	{
		System.out.println("I am inside Test1");
	}		
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void Test2() throws AWTException, InterruptedException
	{
		System.out.println("I am inside Test2");
		Assert.assertTrue(false);
	}
	@Test(priority=2)
	public void Test3() throws AWTException, InterruptedException
	{
		System.out.println("I am inside Test3");
	}
	


}


