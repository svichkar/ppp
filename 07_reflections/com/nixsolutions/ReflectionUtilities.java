package com.nixsolutions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtilities {
	
	public static Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, SecurityException, IllegalAccessException {
		Field f = obj.getClass().getDeclaredField(fieldName);
		if (f.getModifiers() == Modifier.PUBLIC) {
			return f.get(new Object());
		} else {
			throw new IllegalAccessException();
		}
	}
}
