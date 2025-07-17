package HrmFunctions;


import PageObjects.EmployeePage;
import PageObjects.LoginPage;
import constants.FrameworkConstants;
import driver.Driver;
import driver.DriverManager;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import reports.ExtentReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseTest {

	protected LoginPage loginPage;
	protected EmployeePage employeePage;

	@BeforeSuite
    public void setUpSuite(){
        ExtentReport.initReports();
    }

    @AfterSuite
    public void tearDownSuite(){
        ExtentReport.tearDownReport();
    }

    @BeforeMethod
    public void setUp() {
        Driver.initDriver();
        loginPage = PageFactory.initElements(DriverManager.getWebDriver(), LoginPage.class);
        employeePage = PageFactory.initElements(DriverManager.getWebDriver(), EmployeePage.class);
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
	}

	@DataProvider(name = "testData")//,parallel = true)
	public Object[][] testData(Method m) throws IOException {
        String excelFilePath = FrameworkConstants.getInstance().getTestExcelPath();
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet(m.getName());
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i][j] = getCellValue(cell);
            }
        }
        workbook.close();
        fileInputStream.close();
        return data;
	}

    private Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return (int) cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }
}


