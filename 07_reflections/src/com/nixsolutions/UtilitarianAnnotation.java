package com.nixsolutions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * The Class UtilitarianAnnotation - two methods for working with Annotation.
 */
public class UtilitarianAnnotation {

	/**
	 * Gets the field with annotation ToString. Using standard reflect.
	 *
	 * @param obj the obj
	 * @return the field with annotation reflect
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public String getFieldWithAnnotationReflect(Object obj) throws IllegalArgumentException, IllegalAccessException {
		String result = "";
		Field[] allFieldsOfCurrentClass = obj.getClass().getDeclaredFields();
		for (Field field : allFieldsOfCurrentClass) {
			if (field.isAnnotationPresent(ToString.class)) {
				field.setAccessible(true);
				result = field.getName() + ":" + field.get(obj).toString();
			}
		}
		return result;
	}

	/**
	 * Gets the field with annotation ToString. Using Commons.
	 *
	 * @param obj the obj
	 * @return the field with annotation commons
	 * @throws IllegalAccessException the illegal access exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public String getFieldWithAnnotationCommons(Object obj)
			throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		String result = "";
		ArrayList<String> fieldsWithAnnotationToString = new ArrayList();
		Class<? extends Object> objClass = obj.getClass();
		for (Field field : FieldUtils.getFieldsListWithAnnotation(objClass, ToString.class)) {
			fieldsWithAnnotationToString.add(field.getName() + ":" + field.get(obj).toString());
		}
		for (String field : fieldsWithAnnotationToString) {
			result += field + " ";
		}
		return result;
	}

}
