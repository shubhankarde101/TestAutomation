package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Objects;

public class ExtentReport {
    private static ExtentReports extentReports;


    public static void initReports() {
        if (Objects.isNull(ExtentManager.getExtentTest())) {
            extentReports = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/index.html");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setReportName("Automation");
            sparkReporter.config().setDocumentTitle("Test Automation");
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Devices_Name","Mac");
            extentReports.setSystemInfo("Project Name", "Orange HRM");
            extentReports.setSystemInfo("Automation Tester", "SH");
            extentReports.setSystemInfo("Organization", "XYZ");
        }
    }

    public static void tearDownReport() {
        //if (Objects.nonNull(ExtentManager.getExtentTest())) {
            extentReports.flush();
            ExtentManager.unLoad();
        //}
    }

    public static void createTest(String testCaseName) {
        ExtentManager.setExtentTest(
                extentReports.createTest(testCaseName));
    }

    public static void addAuthors(String auth) {
        ExtentManager.getExtentTest().assignAuthor("Test_Case_ID_ " + auth);
    }
}
