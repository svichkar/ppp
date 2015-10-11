package com.nixsolutions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtilities {
	
	public static Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException,
	SecurityException, IllegalAccessException, IllegalArgumentException {
		Field f = obj.getClass().getDeclaredField(fieldName);
		if (f.getModifiers() == Modifier.PUBLIC) {
			return f.get(obj);
		} else {
			throw new IllegalAccessException();
		}
	}
}
