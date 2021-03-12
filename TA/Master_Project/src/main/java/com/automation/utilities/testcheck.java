package com.automation.utilities;

import java.io.IOException;

public class testcheck {

	public static void main(String[] args) throws IOException {
		
		// Testing property file code snippet
		
		PropertiesFile val1 = PropertiesFile.getInstanceOfSingletonPropertyClass();
		System.out.println(val1.readPropertiesFile("Browser"));
		
		val1.setProperty("Testing1", "1234989");
		val1.setProperty("Testing2", "1234984");
		val1.setProperty("Testing3", "1234988");
		
		
		String val2 = val1.readPropertiesFile("Testing1");
		System.out.println(val2);
		
		
		

	}

}
