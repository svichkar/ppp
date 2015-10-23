package com.nixsolutions.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ObjConvertor {

	private Object obj;
	private Class<? extends Annotation> myAnnotation;

	public ObjConvertor(Object anObject, Class<? extends Annotation> annotation) {
		this.obj = anObject;
		this.myAnnotation = annotation;
	}

	public String getNameOfObject() {
		// /it will return the particular field of object that was defined (if toString was
		// overloaded of course as it was done in my case)
		return obj.getClass().cast(obj).toString();
	}

	public String getClassOfObject() {
		return obj.getClass().toString();
	}

	private Map<String, String> getFields() throws IllegalArgumentException,
			IllegalAccessException {
		Map<String, String> result = new HashMap<>();
		for (Field oneField : obj.getClass().getDeclaredFields()) {
			if (oneField.isAnnotationPresent(myAnnotation)) {
				if (oneField.isAccessible()) {
					result.put(oneField.getName(), oneField.get(obj).toString());
				} else {
					oneField.setAccessible(true);
					result.put(oneField.getName(), oneField.get(obj).toString());
				}
			}
		}
		return result;
	}

	public Integer amountOfFoundFields() throws IllegalArgumentException,
			IllegalAccessException {
		return getFields().size();
	}

	public String getObjectAsString() throws IllegalArgumentException,
			IllegalAccessException {
		String result = null;
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(this.getClassOfObject()).append(":")
				.append("[");
		for (Entry<String, String> kv : this.getFields().entrySet()) {
			builder.append(kv.getKey()).append(":").append(kv.getValue());
			builder.append(",");
		}
		builder.append("]").append("]");
		result = builder.toString();
		return result;
	}
}
