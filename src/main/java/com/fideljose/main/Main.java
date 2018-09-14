package com.fideljose.main;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fideljose.model.Persona;
import com.fideljose.util.DataReflection;
import com.fideljose.util.Util;

public class Main {
	
	public static final Logger LOGGER = java.util.logging.Logger.getLogger("MockitoTestingClass");
	static Persona p = new Persona();
	private static final String NODE_PARAM = "banco";
	
	public static void main(String[] args) {
		Util.getDataFromPropertiesFile();
		
		readXMLFile("BANCOLOMBIA");
		//*** Recuperar data del xml
		HashMap<String, String> structure = (HashMap<String, String>) Util.readXMLPropertiesTags("structure.xml");
		builtObject(structure);
		
		DataReflection.getValueFromReflection();
	}
	
	public static Map<String, String> readXMLFile(String parameterBancToSelect) {	
		Map<String, String> map = new HashMap<>();
		try {	
			File fXmlFile = new File("structure.xml");	
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();	
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	
			Document doc = dBuilder.parse(fXmlFile);	
						
			//optional, but recommended	
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work	
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(NODE_PARAM);	 
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Element el = (org.w3c.dom.Element) nList.item(temp);
				String idElementFileXML = el.getAttribute("id");
				LOGGER.log(Level.INFO, "======= idElementFileXML {0}", idElementFileXML);
				if(!idElementFileXML.equals(parameterBancToSelect)) continue;
				
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					LOGGER.log(Level.INFO, eElement.getElementsByTagName("parametro1").item(0).getTextContent());
					LOGGER.log(Level.INFO, eElement.getElementsByTagName("parametro2").item(0).getTextContent());
					LOGGER.log(Level.INFO, eElement.getElementsByTagName("parametro3").item(0).getTextContent());
					LOGGER.log(Level.INFO, eElement.getElementsByTagName("parametro4").item(0).getTextContent());
				}	
			}	
		}catch(ParserConfigurationException er){	
			LOGGER.log(Level.INFO, "readXMLFile Exception ParserConfigurationException ", er);
		}catch(SAXException ex){	
			LOGGER.log(Level.INFO, "readXMLFile Exception SAXException ", ex);
		}catch(IOException e){	
			LOGGER.log(Level.INFO, "readXMLFile Exception IOException ", e);	
		}
		return map;
	}
	
	public static Persona builtObject(Map<String, String> data) {
		data.forEach((k,v) -> {
			String nameAttributeClass = v.trim();
			boolean flag = true;
			switch(nameAttributeClass) {
				case "nombre"	: DataReflection.setValueFromRerflection(flag, nameAttributeClass, "Nombre setteado", p); break;
				case "telefono" : DataReflection.setValueFromRerflection(flag, nameAttributeClass, "Telefono setteado", p); break;
				case "email" 	: DataReflection.setValueFromRerflection(flag, nameAttributeClass, "Email setteado", p); break;
				// Dado parametros de archivo CSV, para que sea este archivo quien manipule el flag.
				default 		: DataReflection.setValueFromRerflection(!flag, nameAttributeClass, "Nunca va a llegar aqui, pero solarLink lo requiere", p); break;
			}
		});
		return p;
	}
	
	public static void test() throws IllegalAccessException {
        Field[] fs = Persona.class.getFields();
        for(Field f : fs) {
            f.setAccessible(true); 
            Object value = f.get(Persona.class);
            if(value == null) {
            	LOGGER.log(Level.INFO, "Respuesta {0}", f.getName());
            }
        }
	}
}