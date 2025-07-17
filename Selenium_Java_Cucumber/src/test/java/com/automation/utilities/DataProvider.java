package com.automation.utilities;
import java.awt.AWTException;
import java.io.IOException;

public class DataProvider{
	
	
	@org.testng.annotations.DataProvider(name="testdataprovider")
	public Object[][] getDatafromExcel() throws AWTException, InterruptedException, IOException
	{		
		String excelfilename = "TestNG_ExcelBook.xlsx";
		ExcelUtil exclutl = new ExcelUtil();
		Object excldata[][]= exclutl.ReadData(System.getProperty("user.dir")+"\\Data\\", excelfilename, "TestNG");	
		return excldata;
	}	
	@org.testng.annotations.Test(dataProvider = "testdataprovider")
	public void Test(Object Username, Object Password)
	{
		
		System.out.println(Username.toString()+"   "+Password.toString());		
		
	}	
}


