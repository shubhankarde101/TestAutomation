package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {
	
	public static Properties properties = null;
	static File file = new File("Data\\Input.properties");
	public static Properties readPropertiesFile()
	{
		
		try		{
			
			FileInputStream fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
			fileInputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return properties;
	}
	
	public static void setProperty(String key, String value) throws IOException
	{
		FileOutputStream out = new FileOutputStream(file, true);
		try {
			
			properties = new Properties();
			properties.setProperty(key, value);
			try {
				properties.store(out, "Added");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		finally{
			
			out.close();
		}
		
		
		
		
		
		
	}
	
	
	

}
