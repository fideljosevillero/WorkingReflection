package com.fideljose.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Util {

	public static void readXMLFile() {
//		try {
//			File fXmlFile = new File("staff.xml");
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(fXmlFile);
//					
//			//optional, but recommended
//			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
//			doc.getDocumentElement().normalize();
//	
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//					
//			NodeList nList = doc.getElementsByTagName("staff");
//					
//			System.out.println("----------------------------");
//	
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//	
//				Node nNode = nList.item(temp);
//						
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());
//						
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//	
//					Element eElement = (Element) nNode;
//	
//					System.out.println("Staff id : " + eElement.getAttribute("id"));
//					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//	
//				}
//			}
//		}catch(ParserConfigurationException er){
//			
//		}catch(SAXException ex){
//			
//		}catch(IOException e){
//			
//		}
	}
	
	public static HashMap<String, String> readXML2() {
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			File file = new File("file2.xml");
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
}
