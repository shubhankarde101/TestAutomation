package com.automation.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageObjects {

	@FindBy(how = How.NAME, using = "email")
	public WebElement contactOutEmail;
	
	@FindBy(how = How.NAME, using = "password")
	public WebElement contactOutPwd;
	
	@FindBy(how = How.XPATH, using = ".//button[.='Login']")
	public WebElement contactOutBtnLogin;
	
	@FindBy(how = How.XPATH, using = ".//*[.='Sign in']")
	public WebElement linkedInBtnSignIn;
	
	
	@FindBy(how = How.CSS, using = "input#username")
	public WebElement linkedInEmail;
	
	@FindBy(how = How.CSS, using = "input#password")
	public WebElement linkedInPwd;
	
	
	@FindBy(how = How.XPATH, using = ".//button[.='Sign in']")
	public WebElement linkedInBtnLogin;
	
	
	@FindBy(how = How.CSS, using = "input.search-global-typeahead__input")
	public WebElement ele_linkedInSearchBox;
	
	public String linkedInEmpSrchResults = "a.app-aware-link";
	@FindBy(how = How.CSS, using = "a.app-aware-link")
	public List<WebElement> ele_linkedInEmpSrchResults;
	
	public String linkedInValidateEmpName = "h1.text-heading-xlarge";
	@FindBy(how = How.CSS, using = "h1.text-heading-xlarge")
	public List<WebElement> ele_linkedInValidateEmpName;
	
	
	
	
	
	
	
	
}
