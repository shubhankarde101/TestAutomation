package com.automation.utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

	private Properties properties;
	private String dataPath = "Data\\Input.properties";
	private static PropertiesFile instanceOfSingletonPropertyFile = null;

	
	private PropertiesFile() throws IOException {

		System.out.println("In propertyfile constructor");
		properties = new Properties();		
		
	}
	
	public static PropertiesFile getInstanceOfSingletonPropertyClass() throws IOException {
		if (instanceOfSingletonPropertyFile == null) {
			System.out.println("Invoking Propertyfile");
			instanceOfSingletonPropertyFile = new PropertiesFile();
		}
		return instanceOfSingletonPropertyFile;
	}
	
	public  String readPropertiesFile(String key) {
		FileInputStream fileInputStream = null;
		try {  

			fileInputStream = new FileInputStream(dataPath);
			properties.load(fileInputStream);
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return properties.getProperty(key);
	}

	public  void setProperty(String key, String value) throws IOException {
		FileOutputStream out = null;
		try {
            out = new FileOutputStream(dataPath, false);
			properties.setProperty(key, value);
			
			try {
				properties.store(out, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {

			out.close();
		}

	}

}
