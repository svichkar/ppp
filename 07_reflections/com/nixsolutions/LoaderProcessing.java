package com.nixsolutions;

import java.lang.reflect.Field;

public class LoaderProcessing {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		CustomLoader cLoader = new CustomLoader("F:\\");
		Class<?> tClass = cLoader.findClass("com.nixsolutions.Account");
		Field f = tClass.getDeclaredField("role");
	}

}
