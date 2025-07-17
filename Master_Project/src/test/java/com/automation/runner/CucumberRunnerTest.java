package com.automation.runner;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.automation.utilities.AutoHealLocator;

import StepDef.BasePage;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(

		features = { "src/test/resources/MyFeature1.feature" }, plugin = { "html:target/cucumber-html-report",
				"json:target/cucumber.json" }, glue = { "StepDef" }, strict = true, dryRun = false, // tags= {"smoke"},
		monochrome = true, snippets = SnippetType.CAMELCASE)

public class CucumberRunnerTest extends BasePage{

	@BeforeClass
	public  static void setUp_junit() {
		
		System.out.println("Before All Test");
		try {
			AutoHealLocator.initializeParser("Crypto Currency Info Page", "ON");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initializeUtils();
		loadDriver();	

	}

	@AfterClass
	public  static void tearDown_junit() {
		
		System.out.println("After All Test");
		closeBrowser();
		AutoHealLocator.write_Content_To_Xml();
   
	}

}
