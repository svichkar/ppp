/**
 * 
 */
package com.nixsolutions;

/**
 * @author mixeyes
 *
 */
public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start test:\n");
		TestAnnotaionClass annotaionClass = new TestAnnotaionClass();
		
		String strAnnoClass = ToStringUtils.convertToString(annotaionClass);
		
		System.out.println(strAnnoClass);
		

	}

}
