package com.nixsolutions.secondtask;

import java.lang.reflect.Method;

public class SecondTaskMain {

	public static void main(String[] args) throws ClassNotFoundException {
		String path = "D:\\JavaCourse\\javappp\\02_oop\\OopLab\\bin";
		String name = "com.nixsolutions.figure.Figure";
		
		CustomClassLoader loader = new CustomClassLoader(path);
		Class<?> clazz = loader.loadClass(name);
		System.out.println("Method list of " + clazz.getSimpleName() + " class:");
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			method.setAccessible(true);
			System.out.println(method.getName());
		}
	}
}