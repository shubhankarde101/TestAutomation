package com.automation.utilities;
import java.awt.AWTException;

import org.testng.annotations.Test;

public class PriDepGrp extends ActionMethods{
	
	@Test(groups={"sanity"}, priority=2)
	public void Test1() throws AWTException, InterruptedException
	{
		System.out.println("I am inside Test1");
	}		
	@Test(dependsOnMethods={"Test3"}, groups={"sanity"}, priority=0)
	public void Test2() throws AWTException, InterruptedException
	{
		System.out.println("I am inside Test2");
	}
	@Test(priority=1)
	public void Test3() throws AWTException, InterruptedException
	{
		System.out.println("I am inside Test3");
	}
	@Test
	public void Test4() throws AWTException, InterruptedException
	{
		System.out.println("I am inside Test4");
	}
	@Test
	public void Test5() throws AWTException, InterruptedException
	{
		System.out.println("I am inside Test5");
	}
}


