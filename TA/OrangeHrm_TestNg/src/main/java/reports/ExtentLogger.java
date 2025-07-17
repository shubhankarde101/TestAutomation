package reports;

import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String;
import static utils.ScreenShotUtils.getBase64Images;

public final class ExtentLogger {

    private ExtentLogger() {

    }

    public static void pass(String passMessage) {
        reports.ExtentManager.getExtentTest().pass(passMessage);
    }

    public static void fail(String failMessage) {
        reports.ExtentManager.getExtentTest().fail(failMessage,
                createScreenCaptureFromBase64String(getBase64Images())
                        .build());
    }

    public static void skip(String skipMessage) {
        reports.ExtentManager.getExtentTest().skip(skipMessage);
    }

    public static void info(String infoMessage) {
        reports.ExtentManager.getExtentTest().info(infoMessage);
    }

}
