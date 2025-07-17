package com.automation.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = { "src/test/resources/OnlineShopTestableUsers.feature" }, plugin = { "pretty", "html:target/cucumber-html-report.html",
		"json:target/cucumber.json" }, glue = { "com.onlineshop.steps" },
		monochrome = true)

public class CucumberRunnerTest {

}
