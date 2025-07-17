package com.iOS.patient.testcases;

import com.affle.iOS.driver.Driver;
import com.affle.iOS.driver.DriverManager;
import com.affle.iOS.enums.LogType;
import com.affle.iOS.reports.ExtentReport;
import com.affle.iOS.reports.FrameWorkLogger;
import com.affle.iOS.utils.AppiumUtil;
import com.affle.iOS.utils.PropertyUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import static io.appium.java_client.remote.IOSMobileCapabilityType.USE_PREBUILT_WDA;

public class BaseTest {


	protected AppiumUtil appiumutil = new AppiumUtil((AppiumDriver) DriverManager.getWebDriver());


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
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();

    }

	protected void launchOtherAPP(String path) throws MalformedURLException {
		FrameWorkLogger.log(LogType.INFO, "Launching Other App now");
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.APP, path);
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"16.2");
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, PropertyUtils.getPropertyValue("udid"));
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,PropertyUtils.getPropertyValue("DevicesName"));
		desiredCapabilities.setCapability(USE_PREBUILT_WDA, true);
		desiredCapabilities.setCapability("autoAcceptAlerts", "true");
		URL url = new URL("http://127.0.0.1:4723/");
		IOSDriver<IOSElement> driver = new IOSDriver<>(url, desiredCapabilities);
		DriverManager.setWebDriver(driver);
	}

	/*@DataProvider(name = "data")
    public Iterator<Object[]> getTestData(Method m) throws Exception{
		 List<Hashtable<String, String>> data = getData(m.getName());
		 List<Object[]> dataList = new ArrayList<Object[]>();
		 Iterator<Hashtable<String, String>> dataIterator =  data.iterator();
		 while(dataIterator.hasNext()){
			 Hashtable<String, String> setdata = dataIterator.next();
			 System.out.println(setdata.get("RunRow"));
			 if(!(setdata.get("RunRow").equalsIgnoreCase("True"))){
				 dataIterator.remove();
			 }else{
				 Object[] dataObj = {setdata};
				 dataList.add(dataObj);
			 }
		 }
		 return dataList.iterator();
    }

    public static List<Hashtable<String, String>> getData(String pk) throws Exception {
		 File file = new File(System.getProperty("user.dir")+"/src/test/resources/Excel/TestData.xlsx");
		 FileInputStream fileInputStream = new FileInputStream(file);
		 XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
		 XSSFSheet xssfSheet = xssfWorkbook.getSheet("SignUp");
		 int lastRowNum = xssfSheet.getLastRowNum();
		 Hashtable<String, String> data = new Hashtable<String, String>();
		 boolean bool = false;
		 for(int i = 1; i <= lastRowNum; i++){
			 if(xssfSheet.getRow(i).getCell(0).getStringCellValue().equals(pk)){
				 bool = true;
				 break;
			 }
		 }
		 if(bool){
			 int sheetCount = xssfWorkbook.getNumberOfSheets();
			 System.out.println(sheetCount);
			 for(int j = 0; j < sheetCount; j++){
				 System.out.println("Sheet");
				 XSSFSheet sheet = xssfWorkbook.getSheetAt(j);
				 int row = sheet.getLastRowNum();
				 System.out.println(row);
				 System.out.println("Row");
				 for(int k = 1 ; k <= row; k++){
					 if(sheet.getRow(k).getCell(0).getStringCellValue().equals(pk)){
						 int cellnum = sheet.getRow(k).getLastCellNum();
						 System.out.println("Cell");
						 System.out.println(cellnum);
						 for(int l = 1; l < cellnum; l++){
							 System.out.println(sheet.getRow(0).getCell(l).getStringCellValue());
							 System.out.println(sheet.getRow(k).getCell(l).getStringCellValue());
							 data.put(sheet.getRow(0).getCell(l).getStringCellValue(),
									 sheet.getRow(k).getCell(l).getStringCellValue()); 
						 }
						 
					 }
				 }
			 }
		 }
		List<Hashtable<String, String>> testdata = new ArrayList<Hashtable<String,String>>();
		testdata.add(data);
		System.out.println("The data is: "+testdata);
		return testdata;
	}*/
}


