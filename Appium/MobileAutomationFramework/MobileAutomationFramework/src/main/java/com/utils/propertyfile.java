package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Singleton Design Pattern

public class propertyfile {

	private static propertyfile instanceOfSingletonPropertyFile = null;
	Properties prop;

	private propertyfile() throws IOException {

		System.out.println("In propertyfile constructor");
		InputStream input = new FileInputStream(System.getProperty("user.dir") + "/data/propertyfile.properties");
		prop = new Properties();
		// load a properties file
		prop.load(input);
		System.out.println("In propertyfile constructor/loading done");
	}

	public static propertyfile getInstanceOfSingletonPropertyClass() throws IOException {
		if (instanceOfSingletonPropertyFile == null) {
			System.out.println("Invoking Propertyfile");
			instanceOfSingletonPropertyFile = new propertyfile();
		}
		return instanceOfSingletonPropertyFile;
	}

	// To get value
	public String getValue(String key) {
		System.out.println("Fetching the value from key");
		return prop.getProperty(key);
	}

}
