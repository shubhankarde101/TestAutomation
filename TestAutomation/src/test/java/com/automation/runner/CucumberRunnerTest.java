package com.automation.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)

@CucumberOptions(
		
		features= {"src/test/resources/MyFeature1.feature"},
				 plugin = { 
						    "html:target/cucumber-html-report",
						    "json:target/cucumber.json"
						        },
				 glue = {"StepDef"},strict = true,
				   dryRun= false, //tags= {"smoke"},  
				   monochrome = true, snippets= SnippetType.CAMELCASE)
		
		  
public class CucumberRunnerTest {
		

		
	 

}
