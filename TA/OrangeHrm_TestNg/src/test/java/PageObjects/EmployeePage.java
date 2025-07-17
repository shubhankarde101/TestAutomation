package PageObjects;

import driver.DriverManager;
import enums.LogType;
import enums.WaitStrategy;
import factory.ExplicitWaitFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import reports.FrameWorkLogger;
import utils.RandomUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeePage {

    private String empFirstName="";
    private String empLastName="";
    private String empId="";


    @FindBy(xpath = ".//a[@class='oxd-main-menu-item']//span")
    private List<WebElement> lnkMenuItems;

    @FindBy(xpath = ".//a[text()='Add Employee']")
    private WebElement btnAddEmployee;

    @FindBy(xpath = ".//input[@name='firstName']")
    private WebElement inputFirstname;

    @FindBy(xpath = ".//input[@name='lastName']")
    private WebElement inputLastname;

    @FindBy(xpath = "//*[text()='Employee Id']//parent::div//following-sibling::div/input")
    private WebElement inputEmpId;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement btnSave;

    @FindBy(css = "div.orangehrm-edit-employee-content")
    private WebElement divEditEmpContent;

    @FindBy(xpath = ".//a[text()='Employee List']")
    private WebElement lnkEmpList;

    @FindBy(xpath = ".//div[@class='oxd-table-card']//div[@role='row']//button//*[contains(@class, 'trash')]")
    private WebElement btnDeleteEmp;

    @FindBy(xpath = ".//button[text()=' Yes, Delete ']")
    private WebElement btnYesDelete;

    @FindBy(css = "p.oxd-userdropdown-name")
    private WebElement btnUserDrpDwn;

    @FindBy(xpath = ".//a[text()='Logout']")
    private WebElement btnLogout;

    @FindBy(css = "div.orangehrm-login-error")
    private WebElement firstPageElement;

    public void addEmployee(String fName, String lName) {
        FrameWorkLogger.log(LogType.INFO, "Adding an employee");
        lnkMenuItems.stream().filter(x->x.getAttribute("innerText").trim()
                .equalsIgnoreCase("pim")).findFirst()
                        .get().click();
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, btnAddEmployee);
        FrameWorkLogger.log(LogType.PASS_AND_SCREEN_CAPTURE, "Employee Page");
        btnAddEmployee.click();
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, inputFirstname);
        this.empFirstName = fName;
        this.empLastName = lName;
        inputFirstname.sendKeys(empFirstName);
        inputLastname.sendKeys(empLastName);
        empId = inputEmpId.getAttribute("value");
        FrameWorkLogger.log(LogType.INFO, "Employee details entered");
        FrameWorkLogger.log(LogType.PASS_AND_SCREEN_CAPTURE, "Employee details");
        btnSave.click();
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, divEditEmpContent);
        FrameWorkLogger.log(LogType.INFO, "Employee added");

    }

    public boolean validateEmployee()
    {
        FrameWorkLogger.log(LogType.INFO, "Validating that employee is showing in the grid");
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, lnkEmpList);
        lnkEmpList.click();
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, inputEmpId);
        inputEmpId.click();
        inputEmpId.clear();
        inputEmpId.sendKeys(empId);
        btnSave.click();
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, btnDeleteEmp);
        FrameWorkLogger.log(LogType.PASS_AND_SCREEN_CAPTURE, "Employee list Page");
        int size = DriverManager.getWebDriver().findElements(By
                .xpath(".//div[@class='oxd-table-card']//div[@role='row']//div[text()='"+empId+"']")).size();
        FrameWorkLogger.log(LogType.INFO, "Employee validated successfully");
        return size==1;
    }


    public boolean deleteEmployee()
    {
        FrameWorkLogger.log(LogType.INFO, "Validating that employee is deleted");
        btnDeleteEmp.click();
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, btnYesDelete);
        btnYesDelete.click();
        int size = DriverManager.getWebDriver().findElements(By
                .xpath("div[@class='oxd-table-card']//div[@role='row']//div[text()='"+empId+"']")).size();
        FrameWorkLogger.log(LogType.PASS_AND_SCREEN_CAPTURE, "Employee deleted successfully");
        FrameWorkLogger.log(LogType.INFO, "Going to log out");
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, btnUserDrpDwn);
        btnUserDrpDwn.click();
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, btnLogout);
        btnLogout.click();
        ExplicitWaitFactory.performExplicitWaitWait(WaitStrategy.VISIBLE, firstPageElement);
        FrameWorkLogger.log(LogType.INFO, "logged out");
        FrameWorkLogger.log(LogType.PASS_AND_SCREEN_CAPTURE, "Logged out");
        return size==0;
    }

}
