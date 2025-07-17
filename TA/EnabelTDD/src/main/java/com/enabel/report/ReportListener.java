package com.enabel.report;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.enabel.startup.BaseTest;

import com.enabel.utils.SeleniumUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        SeleniumUtils.reportPrint();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        SeleniumUtils.reportPrint();
    }


}
