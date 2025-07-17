package HrmFunctions;

import FrameWorkAssertion.FrameWorkSoftAssertions;
import enums.LogType;
import frameWorkAnnotation.FrameWorkAnnotation;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import listeners.ListenersClass;
import listeners.AnnotationTransformers;
import reports.FrameWorkLogger;


@Listeners({ListenersClass.class, AnnotationTransformers.class})
public class LoginE2EFlowTest extends BaseTest {

    FrameWorkSoftAssertions frameWorkSoftAssertions = new FrameWorkSoftAssertions();

    @FrameWorkAnnotation(TESTID = "Verify user is able to /**/login, add and delete an employee")
    @Test(dataProvider = "testData")
    public void addEmployeeE2EFlow(String firstName, String lastName) {
        loginPage.login();
        employeePage.addEmployee(firstName,lastName);
        frameWorkSoftAssertions.assertTrue(employeePage.validateEmployee(),"Employee validation failed");
        frameWorkSoftAssertions.assertTrue(employeePage.deleteEmployee(),"Employee not deleted");
        FrameWorkLogger.log(LogType.INFO, "Employee deleted successfully");
    }


}
