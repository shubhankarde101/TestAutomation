package com.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class APIDemoHomePage extends BasePage{

	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Views']")
	@iOSXCUITFindBy(xpath="//*[@text='Views']")
	private MobileElement textview_views;
	
	

	public APIDemoViewsPage clickview() throws InterruptedException {
		
		scrollToSpecificElementandClick(MobileBy.xpath("//android.widget.TextView[@text='Views']"));
		//click(textview_views);		
		return new APIDemoViewsPage();
	}

}
