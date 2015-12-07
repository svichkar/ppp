package com.nixsolutions;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnnotationProcessor {

	private static final Logger LOG = LogManager.getLogger(AnnotationProcessor.class.getName());

	public static void main(String[] args) throws ClassNotFoundException {
		// Task#1
		Object atribute1 = "333";
		int atribute3 = 222;
		Object test = new SimpleClass(atribute1, "111", atribute3);
		System.out.println(AnnotationProcessor.processField(test));
		System.out.println(test.toString());

		// Task#2
		PathClassLoaderImpl loader = new PathClassLoaderImpl();
		loader.setPath("D:/EclipseWorkspaceDirectory/IOLab/bin/");
		Class customClass = loader.loadClass("com.nixsolutions.IOLab");
		String nameOfCustClass = customClass.getName();
		System.out.println(nameOfCustClass);
	}

	private static String processField(Object test) {
		String res = "";
		Field[] field = test.getClass().getDeclaredFields();

		for (Field fd : field) {
			if (fd.isAnnotationPresent(ToString.class)) {
				fd.setAccessible(true);
				try {
					res += fd.getName() + ": " + fd.get(test).toString() + "; ";
				} catch (IllegalArgumentException | IllegalAccessException e) {
					LOG.error(e, e);
				}
			}
		}
		return res;
	}

}
