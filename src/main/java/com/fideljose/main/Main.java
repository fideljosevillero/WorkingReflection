package com.fideljose.main;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fideljose.model.Persona;
import com.fideljose.util.DataReflection;
import com.fideljose.util.Util;

public class Main {
	
	public static final Logger LOGGER = java.util.logging.Logger.getLogger("MockitoTestingClass");
	static Persona p = new Persona();
	
	public static void main(String[] args) {
		//*** Recuperar data del xml
		HashMap<String, String> structure = Util.readXML2();
		builtObject(structure);
		
		DataReflection.getValueFromReflection();
	}
	
	public static Persona builtObject(Map<String, String> data) {
		data.forEach((k,v) -> {
			String nameAttributeClass = v;
			switch(v) {
				case "nombre"	: DataReflection.setValueFromRerflection(nameAttributeClass, "valor a settear"); break;
				case "telefono" : DataReflection.setValueFromRerflection(nameAttributeClass, "valor a settear 2"); break;
				case "email" 	: DataReflection.setValueFromRerflection(nameAttributeClass, "valor a settear 3"); break;
				default			: DataReflection.setValueFromRerflection(nameAttributeClass, "valor por defecto"); break; 
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