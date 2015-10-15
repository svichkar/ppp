package com.nixsolutions.classloader;

import java.lang.reflect.Field;

/**Class for validating our implementation of ClassLoader
 * 
 * @author maxb
 *
 */
public class CallMyClassLoader {

	public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, SecurityException {
		MyClassLoader myClassLoader = new MyClassLoader(
				"D:\\WorkPlace\\Java_lessons_maxb\\temp",
				ClassLoader.getSystemClassLoader());
		Class<?> anClass = myClassLoader.loadClass("com.nixsolutions.Car");
		Field ff = anClass.getDeclaredField("nameOfCar");
		System.out.println(String.format("Found field is %s", ff.getName()));
		System.out.println(String.format("Found class is %s", anClass.getName()));
		Class<?> classString = myClassLoader.loadClass("org.apache.commons.io.FileUtils");
		System.out.println(String.format("Found native class is %s", classString.getName()));
	}
}
