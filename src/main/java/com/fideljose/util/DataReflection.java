package com.fideljose.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fideljose.model.Persona;

public class DataReflection {
	
	public static final Logger LOGGER = java.util.logging.Logger.getLogger("MockitoTestingClass");
	
	private DataReflection() {
		super();
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

	public static void setValueFromRerflection(boolean flag, String fieldName, String valueField, Persona p) {
		if(flag) return;
		
		Class<?> c = Persona.class;
		try {
			Field field = c.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(p, valueField);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Exception setValueFromRerflection {0}", e.getStackTrace());
		}
	}
	
	public static void getAllAttributeNameFromClass() {
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
