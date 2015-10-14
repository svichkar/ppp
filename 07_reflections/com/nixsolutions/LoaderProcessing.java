package com.nixsolutions;

import java.lang.reflect.Field;

public class LoaderProcessing {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		CustomLoader cLoader = new CustomLoader("D:\\Java Courses\\javappp\\lab1\\out\\production\\lab1\\com\\nixsolutions", ClassLoader.getSystemClassLoader());
		Class<?> tClass = cLoader.findClass("com.nixsolutions.Account");
		Field f = tClass.getDeclaredField("role");
		Class<?> strClass = cLoader.findClass("java.lang.String");
	}

}
