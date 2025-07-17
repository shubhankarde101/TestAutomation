package com.affle.iOS.Listeners;


import com.affle.iOS.enums.LogType;
import com.affle.iOS.frameWorkAnnotation.FrameWorkAnnotation;
import com.affle.iOS.reports.ExtentLogger;
import com.affle.iOS.reports.ExtentReport;
import com.affle.iOS.reports.FrameWorkLogger;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersClass implements ITestListener, ISuiteListener {
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport
                .createTest(result.getName());
        ExtentReport.addAuthors(result.getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(FrameWorkAnnotation.class)
                .TESTID());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        FrameWorkLogger.log(LogType.PASS, result.getName() + " is Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        FrameWorkLogger.log(LogType.FAIL, result.getName() + " is Failed");
        FrameWorkLogger.log(LogType.SCREEN_CAPTURE, result.getName());
      //  FrameWorkLogger.log(LogType.FAIL,result.getThrowable().toString());
        ExtentLogger.fail(result.getThrowable().toString());
        // ExtentLogger.fail();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        FrameWorkLogger.log(LogType.SKIP, result.getName() + " is skipped");
    }


}
