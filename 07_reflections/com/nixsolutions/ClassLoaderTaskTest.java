package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClassLoaderTaskTest {
	private static final Logger LOG = LogManager.getRootLogger();

	public static void main(String[] args) {
		MyClassLoader loader = new MyClassLoader();
		loader.setPath("D:\\Java\\eclipse\\workspace\\JavaTraining\\Laba_03_Strings");
		Class loadClass = null;
		try {
			loadClass = loader.loadClass("com.nixsolutions.Strings");
		} catch (ClassNotFoundException e) {
			LOG.catching(e);
		}
		LOG.info("Loaded class name: " + loadClass.getName());
	}

}
