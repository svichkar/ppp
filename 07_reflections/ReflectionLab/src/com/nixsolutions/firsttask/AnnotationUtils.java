package com.nixsolutions.firsttask;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class AnnotationUtils {

	public static String getStringRepresStandart(Object obj)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		Class<? extends Object> objClass = obj.getClass();
		StringBuilder strBuilder = new StringBuilder(objClass.getSimpleName() + " [");
		for (Field field : objClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(ToString.class)) {
				String fieldName = field.getName();
				PropertyDescriptor propDescr = new PropertyDescriptor(fieldName, objClass);
				Method getter = propDescr.getReadMethod();
				strBuilder.append(fieldName).append("=").append(getter.invoke(obj)).append(",");
			}
		}
		strBuilder.replace(strBuilder.lastIndexOf(","), strBuilder.length(), "").append("]");
		return strBuilder.toString();
	}

	public static String getStringRepresCommons(Object obj)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Class<? extends Object> objClass = obj.getClass();
		StringBuilder strBuilder = new StringBuilder(objClass.getSimpleName() + " [");
		for (Field field : FieldUtils.getFieldsListWithAnnotation(objClass, ToString.class)) {
			String fieldName = field.getName();
			strBuilder.append(fieldName).append("=").append(PropertyUtils.getProperty(obj, fieldName)).append(",");
		}
		strBuilder.replace(strBuilder.lastIndexOf(","), strBuilder.length(), "").append("]");
		return strBuilder.toString();
	}

}
