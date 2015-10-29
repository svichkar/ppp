/**
 * 
 */
package com.nixsolutions;

import java.io.IOException;

/**
 * @author mixeyes
 *
 */
public class ClassLoaderTest {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		System.out.println("Start test:\n");
		TestAnnotaionClass annotaionClass = new TestAnnotaionClass();

		String strAnnoClass = ToStringUtils.convertToString(annotaionClass);

		System.out.println(strAnnoClass);

		CustomClassLoader loader = new CustomClassLoader(ClassLoaderTest.class.getClassLoader());
		String curDir = System.getProperty("user.dir");
		loader.setPath(curDir+"\\ExtendsClasses\\");
		Object newClazz = loader.getCustomClass("com.nixsolutions.CustomCollection");


	}

}
