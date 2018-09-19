package com.fideljose.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
	
	private static final Logger LOGGER = java.util.logging.Logger.getLogger(Util.class.getName());
	
	private Util() {
		throw new IllegalStateException("Utility class cannot be instantiated");
	}
	
	public static Map<String, String> readXMLPropertiesTags(String nameFileToBanc) {
		HashMap<String, String> map = new HashMap<>();
		try(FileInputStream fileInput = new FileInputStream(new File(nameFileToBanc))){
			Properties properties = new Properties();
			properties.loadFromXML(fileInput);

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				map.put(key, value);
			}
			return map;
		}catch(Exception er) {
			LOGGER.log(Level.INFO, "Exception readXML2() => {0} ", er);
		}
		return new HashMap<>();
	}
	
	public static void getDataFromPropertiesFile() {
		ResourceBundle rb = ResourceBundle.getBundle("parameters");
		String value = rb.getString("email");
		LOGGER.log(Level.INFO, "value from properties file => {0} ", value);
	}
	
}

