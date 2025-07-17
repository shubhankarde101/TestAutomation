package listeners;

import enums.LogType;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentLogger;
import reports.ExtentManager;
import reports.ExtentReport;
import reports.FrameWorkLogger;

public class ListenersClass implements ITestListener, ISuiteListener {
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport
                .createTest(result.getName());
        ExtentReport.addAuthors(result.getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(frameWorkAnnotation.FrameWorkAnnotation.class)
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
        ExtentLogger.fail(result.getThrowable().toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        FrameWorkLogger.log(LogType.SKIP, result.getName() + " is skipped");
    }


}
