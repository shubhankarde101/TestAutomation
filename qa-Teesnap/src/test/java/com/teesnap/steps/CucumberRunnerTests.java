package com.teesnap.steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features", glue = "com.teesnap.steps")
public class CucumberRunnerTests  extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}