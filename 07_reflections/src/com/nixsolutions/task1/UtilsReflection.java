package com.nixsolutions.task1;

import java.lang.reflect.Field;

public class UtilsReflection {
    @SuppressWarnings("unchecked")
    public static <T> T getValue(Object o, String fieldName) throws NoSuchFieldException, SecurityException, IllegalAccessException {
	Field f = o.getClass().getDeclaredField(fieldName);
	f.setAccessible(true);
	if (f.isAnnotationPresent(Public.class)) {
	    return (T) f.get(o);
	} else {
	    throw new IllegalAccessException();
	}
    }
}
