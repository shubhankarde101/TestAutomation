package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {	
  public Object[][] ReadData(String PathName,String filename, String SeetName)  throws IOException  
  {
	 Workbook Exceltest = null;	 
	 File file = new File(PathName+filename);     
	 FileInputStream inputStream = new FileInputStream(file);	 	
	 String fileExtensionName = filename.substring(filename.indexOf("."));	 
	 if(fileExtensionName.equals(".xlsx")){	 
		  Exceltest = new XSSFWorkbook(inputStream);
	   }	    
	    else if(fileExtensionName.equals(".xls")){	       
	     Exceltest = new HSSFWorkbook(inputStream);
	   }
	 Sheet sheet = Exceltest.getSheet(SeetName);
	 int rowcount = RowCount(sheet);
	 int colcount = ColCount(sheet);
	 Object data[][] = new Object[rowcount-1][colcount];
	 for(int i=1;i<rowcount;i++)
	 {
		 for(int j=0;j<colcount;j++)
		 {			 
			 data[i-1][j]= sheet.getRow(i).getCell(j);			 
			 
		 }
	 }		 
	 return data;
  }	
  public int RowCount(Sheet s1)
  {
	  int row = 0;
	  row = s1.getPhysicalNumberOfRows();
	  return row;	  
	  
  }
  public int ColCount(Sheet s2)
  {
	  int c1 = 0;
	  c1 = s2.getRow(0).getPhysicalNumberOfCells();
	  return c1;  
	  
  }  
}
