package com.nixsolutions.reflections;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task2Test {
	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {

		MyClassLoader loader = new MyClassLoader();
		loader.setPath("D:\\06_collections");
		Class someClass = null;
		try {
			someClass = loader.loadClass("com.nixsolutions.Experiments2");
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
		LOG.debug(someClass.getName());

		Method[] methods = someClass.getDeclaredMethods();
		for (Method method : methods) {
			LOG.debug(method.getName());
		}
	}
}
