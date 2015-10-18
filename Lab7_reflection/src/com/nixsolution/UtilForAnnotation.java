package com.nixsolution;

import java.lang.reflect.Field;

public class UtilForAnnotation {
	
	
	public String getAnotetadField(Object obj) throws IllegalArgumentException, IllegalAccessException{
		String toReturn = "";
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields){
			if(field.isAnnotationPresent(ToString.class)){
				field.setAccessible(true);
				toReturn = field.get(obj).toString();
			}
		}
		return toReturn;
	}
}
