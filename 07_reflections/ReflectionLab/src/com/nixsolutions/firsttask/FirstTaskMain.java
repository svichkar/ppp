package com.nixsolutions.firsttask;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class FirstTaskMain {

	public static void main(String[] args) {
		Person personObj = new Person("Tom", "Riddle", "Magician", 16);
		System.out.println("Simple toString method: " + personObj.toString());
		try {
			System.out.println("Using standart classes: " + AnnotationUtils.getStringRepresStandart(personObj));
			System.out.println("Using Apache Commons lib: " + AnnotationUtils.getStringRepresCommons(personObj));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
