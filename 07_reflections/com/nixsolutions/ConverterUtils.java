package com.nixsolutions;

import java.lang.reflect.Field;
import org.reflections.ReflectionUtils;

public class ConverterUtils {

	public static String getFields(Object obj) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder result = new StringBuilder();
		result.append("[").append(obj.getClass().getName()).append("] ");
		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(ToString.class)) {
				field.setAccessible(true);
				result.append(field.getName() + "=" + field.get(obj).toString() + "; ");
			}
		}
		result.setLength(result.length() - 2);
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getFieldsGoogle(Object obj) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder result = new StringBuilder();
		result.append("[").append(obj.getClass().getName()).append("] ");
		for (Field field : ReflectionUtils.getFields(obj.getClass(), ReflectionUtils.withAnnotation(ToString.class))) {
			field.setAccessible(true);
			result.append(field.getName() + "=" + field.get(obj).toString() + "; ");
		}
		result.setLength(result.length() - 2);
		return result.toString();
	}

}
