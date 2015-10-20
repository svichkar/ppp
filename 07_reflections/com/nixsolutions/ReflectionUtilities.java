package com.nixsolutions;

import java.lang.reflect.Field;

public class ReflectionUtilities {
	
	public static Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException,
	SecurityException, IllegalAccessException, IllegalArgumentException {
		Field f = obj.getClass().getDeclaredField(fieldName);
		f.setAccessible(true);
		if (f.isAnnotationPresent(Public.class)) {
			return f.get(obj);
		} else {
			throw new IllegalAccessException();
		}
	}
}
