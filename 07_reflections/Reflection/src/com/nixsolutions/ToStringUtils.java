package com.nixsolutions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ToStringUtils {
	
	public static String convertToString(Object annotationObject) {
		String resultString="";
		List<Field> publicFields = (List<Field>) Arrays.stream(annotationObject.getClass().getFields()).filter(field -> field.getAnnotation(ToString.class)!=null).collect(Collectors.toList());
		List<Field> declaredFields = (List<Field>) Arrays.stream(annotationObject.getClass().getDeclaredFields()).filter(field -> field.getAnnotation(ToString.class)!=null).collect(Collectors.toList());
		
		for(Field field: publicFields){
			resultString+= "publicFields: "; 
			resultString+= field.getName()+"; \n";
		}
		
		for(Field field: declaredFields){
			resultString+= "declaredFields: "; 
			resultString+= field.getName()+"; \n";
		}
		
		
		return resultString;
	}

}
