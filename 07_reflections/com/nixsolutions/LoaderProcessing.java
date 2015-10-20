package com.nixsolutions;

import java.lang.reflect.Field;

public class LoaderProcessing {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		CustomLoader cLoader = new CustomLoader("F:\\temp", ClassLoader.getSystemClassLoader());
		Class<?> tClass = cLoader.findClass("com.nixsolutions.Account");
		Field f = tClass.getDeclaredField("role");
		Class<?> strClass = cLoader.findClass("java.lang.String");
		try {
			Class<?> fTest = cLoader.findClass("com.nixsolutions.model.User");
		} catch (ClassNotFoundException ex) {
			System.out.println("Catched the intended exception.");
		}
		//D: is an optical drive at my pc - so this will throw stack trace of the runtime exception
		//but will still find this class as it is located in the classpath
		cLoader = new CustomLoader("D:\\", ClassLoader.getSystemClassLoader());
		tClass = cLoader.findClass("com.nixsolutions.Account");
	}

}
