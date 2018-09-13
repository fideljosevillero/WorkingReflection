package com.fideljose.main;


import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fideljose.model.Persona;

public class Main {
	
	public static final Logger LOGGER = java.util.logging.Logger.getLogger("MockitoTestingClass");

	public static void main(String[] args) {
		getValueFromReflection();
		setValueFromRerflection();
		getAttributeNameFromClass();
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
	
	public static void getValueFromReflection() {
		Persona p = new Persona();
		Class<?> c = Persona.class;
		try {
			String fieldName = "nombre";
			Field field = c.getDeclaredField(fieldName);
			field.setAccessible(true);
			String valor = field.get(p).toString();
		
			LOGGER.log(Level.INFO, "El valor de la propiedad es [ {0} ]", valor);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Exception getValueFromReflection {0}", e.getStackTrace());
		}
	}
	
	public static void setValueFromRerflection() {
		String fieldName = "edad";
		Persona p = new Persona();
		Class<?> c = Persona.class;
		try {
			Field field = c.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.setInt(p, 17);
			Integer valor = (Integer) field.get(p);
			LOGGER.log(Level.INFO, "edad seteada- {0}", valor);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Exception setValueFromRerflection {0}", e.getStackTrace());
		}
	}
	
	public static void getAttributeNameFromClass() {
		try {
			Class<?> c = Persona.class;
			Field[] listField = c.getDeclaredFields();
			for(Field f : listField) {
					LOGGER.log(Level.INFO, "Attribute name => {0}", f.getName());
			}
		}catch(Exception er) {
			LOGGER.log(Level.INFO, "Exception setValueFromRerflection {0}", er.getStackTrace());
		}
	}
}