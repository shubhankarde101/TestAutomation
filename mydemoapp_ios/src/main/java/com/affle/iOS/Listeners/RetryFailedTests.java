package com.affle.iOS.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {
    /**
     * Returns true if the test method has to be retried, false otherwise.
     *
     * @param result The result of the test method that just ran.
     * @return true if the test method has to be retried, false otherwise.
     */

    private int countTest = 0;
    private int limit =2;
    @Override
    public boolean retry(ITestResult result) {
        boolean value = countTest<limit ;
        countTest++;
        return value;
    }
}
