package com.testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.constants.Constants;
import com.driver.DriverManager;
import com.reports.ExtentReport;
import com.utils.AppiumUtils;
import com.utils.TestUtils;
import com.utils.propertyfile;

import io.appium.java_client.service.local.AppiumDriverLocalService;

/*
 * 
 * Only APIDemosTest is valid for API demo app. Other tests are invalid
 * All test classes needs to extend BaseTest
 * 
 */
public class BaseTest {

	public static AppiumDriverLocalService service;
	public propertyfile pf;
	AppiumUtils au;

	BaseTest() throws IOException {

		System.out.println("----------------In Base Page Constructor---------------------");
		pf= propertyfile.getInstanceOfSingletonPropertyClass();		
		startServer().start();
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public AppiumDriverLocalService startServer() {
		//
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();

		}
		return service;

	}

	@BeforeSuite
	public void beforeSuite() throws Exception {
		
		ExtentReport.initialize();
		TestUtils.deleteFolder(new File(System.getProperty("user.dir") + "\\Screenshots"));
		System.out.println("----------------In Before Suite method---------------------");

	}

	@AfterMethod
	public static void wrapUp() throws InterruptedException {

		DriverManager.setDriver(null);

	}

	@AfterSuite
	public void afterSuite() throws Exception {

		ExtentReport.report.flush();
		File htmlFile = new File(Constants.EXTENTREPORTPATH);
		Desktop.getDesktop().browse(htmlFile.toURI());
		service.stop();

	}

}
