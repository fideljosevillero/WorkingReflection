package com.fideljose.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Util {
	
	public static HashMap<String, String> readXML2(String nameFileToBanc) {
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			File file = new File(nameFileToBanc);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.loadFromXML(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				map.put(key, value);
//				System.out.println(key + ": " + value);
			}
		}catch(Exception er) {
			System.out.println("error xml2 " + er);
		}
		return map;
	}
	
	public static void getDataFromPropertiesFile() {
		Locale.setDefault(new Locale("es, ES"));//("es_CO"));
		//ResourceBundle rb = ResourceBundle.getBundle("C:"+File.separator+"Users"+File.separator+"fivillero"+File.separator+"Documents"+File.separator+"workspace-sts-3.9.5.RELEASE"+File.separator+"WorkingXML"+File.separator+"parameters");
		ResourceBundle rb = ResourceBundle.getBundle("parameters");
		String value = rb.getString("email");
		System.out.println("value from properties file " + value);
	}
	
}
