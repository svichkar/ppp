package com.nixsolutions.task2;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionTask2 {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException,
	    NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException,
	    NoSuchFieldException, ClassNotFoundException {
	CustClassLoader loader = new CustClassLoader();
	loader.setPath("E:/JavaProjects/paryshev.a/javappp/07_reflections/bin/com/nixsolutions/task1");
	Class customClass = loader.loadCustClass("Human");
	String nameOfCustClass = customClass.getName();
	System.out.println(nameOfCustClass);
	Object obj = customClass.getDeclaredConstructor(new Class[] { String.class, String.class }).newInstance("John",
		"Smith");
	Field fld = obj.getClass().getDeclaredField("firstName");
	fld.setAccessible(true);
	System.out.println(fld.get(obj));
    }
}