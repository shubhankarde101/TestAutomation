package com.automation.utilities;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelHandle {
	// WebDriver Reference
	WebDriver driver;

	// This code snippets will fetch the latest file from the folder of given path
	private File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);
		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}

		return theNewestFile;
	}

	/*
	 * This is your excel validation code. I have implemented using .xls extension.
	 * 
	 * If it is .xlsx then replace HSSF to XSSF.
	 * 
	 * Two Apache POI dependency I am adding here to make this compatible in your
	 * system
	 * 
	 * <dependency> <groupId>org.apache.poi</groupId>
	 * <artifactId>poi-ooxml</artifactId> <version>4.1.2</version> </dependency>
	 * 
	 * <!-- https://mvnrepository.com/artifact/org.apache.poi/poi --> <dependency>
	 * <groupId>org.apache.poi</groupId> <artifactId>poi</artifactId>
	 * <version>4.1.2</version> </dependency>
	 */

	private void validateExcelFile(File latestF) throws IOException, InvalidFormatException {
		// TODO Auto-generated method stub
		HSSFWorkbook wb = null;
		InputStream ExcelFileToRead = null;
		try {
			ExcelFileToRead = new FileInputStream(latestF);
			wb = new HSSFWorkbook(ExcelFileToRead);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					cell = (HSSFCell) cells.next();
					System.out.println(cell.getCellType());
					if (cell == null || cell.getCellType() == CellType.BLANK) {
						// This cell is empty
						Assert.fail("Scenario Failed as the excel contains null value");

					} else if (cell.getCellType() == CellType.FORMULA) {
						Assert.fail("Scenario Failed as the excel contains null value");

					} else if (cell.getCellType() == CellType.NUMERIC) {
						System.out.println(cell.getNumericCellValue());

					} else if (cell.getCellType() == CellType.STRING
							&& cell.getStringCellValue().equalsIgnoreCase("null")) {
						Assert.fail("Scenario Failed as the excel contains null value");
						System.out.println("--------------NULL Present---------------");
					}

					else if (cell.getCellType() == CellType.STRING) {

						System.out.println(cell.getStringCellValue());

					} else if (cell.getCellType() == CellType.BOOLEAN) {
						System.out.println(cell.getStringCellValue());
					} else if (cell.getCellType() == CellType.ERROR) {
						Assert.fail("Scenario Failed as the excel contains null value");

					} else {
						Assert.fail("Scenario Failed as the excel contains null value");
					}
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			wb.close();
			ExcelFileToRead.close();
		}

	}

	@BeforeTest
	public void setUpTest() {

		/*
		 * Used Boni Garcia dependency here for WebdriverManager. No driver exe
		 * required. Dependency attached below
		 * 
		 * <dependency> <groupId>io.github.bonigarcia</groupId>
		 * <artifactId>webdrivermanager</artifactId> <version>4.2.2</version>
		 * <scope>test</scope> </dependency>
		 */

		// My chrome version is 87. Change as per your requirement
		WebDriverManager.chromedriver().driverVersion("87").setup();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setAcceptInsecureCerts(true);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=1400,800");
		options.addArguments("disable-infobars"); //
		//options.addArguments("--headless");
		options.merge(cap);
		if (driver == null)
			driver = new ChromeDriver(options);
	}

	@Test
	public void googleSearch1() throws InterruptedException, InvalidFormatException {
		// Sample demo site to replicate your scenario. After clicking on link the excel
		// would be downloaded
		driver.get("https://file-examples.com/index.php/sample-documents-download/sample-xls-download/");
		driver.manage().window().maximize();
		WebElement lnkToDownload = driver.findElement(By.xpath("(.//a[.='Download sample xls file'])[1]"));
		lnkToDownload.click();
		// Adjust this wait time as per your file download latency
		Thread.sleep(5000);
		// Paste your download folder path along with the downloaded file extension
		File latest = getTheNewestFile("C:/Users/SHUBHANKAR/Downloads", "xls");
		// Printing the latest downloaded file name
		System.out.println(latest.getName());
		try {
			validateExcelFile(latest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Closing the driver

	@AfterTest
	public void TearDown() {
		driver.quit();
	}

}
