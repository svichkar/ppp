package com.nixsolution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lab7Task2 {
	private static final Logger LOG = LogManager.getLogger("Task2");

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String pathToClass = System.getProperty("user.dir");
		LOG.info(pathToClass);
		CustomClassLoader customClassLoader = new CustomClassLoader(pathToClass, ClassLoader.getSystemClassLoader());
		Class<?> myClass = customClassLoader.loadClass("com.nixsolution.TestClass");
		TestClass testClass = (TestClass) myClass.newInstance();
		LOG.info(testClass.toString());
		Class<?> javaClass = customClassLoader.loadClass("java.lang.String");
		String string = (String) javaClass.newInstance();
		LOG.info(string.getClass().toString());
	}
}
