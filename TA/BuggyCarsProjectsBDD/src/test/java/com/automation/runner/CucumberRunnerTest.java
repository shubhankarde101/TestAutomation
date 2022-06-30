package com.automation.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(

		features = { "src/test/resources/Top5.feature" }, plugin = { "html:target/cucumber-html-report",
				"json:target/cucumber.json" }, glue = { "com.buggycar.steps" }, strict = true, dryRun = false, 
				// tags= {"smoke"},
		monochrome = true)

public class CucumberRunnerTest {

}
