/**
 * 
 */
package com.nixsolutions;

public class TestConverter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringConverter<Integer[], String>sConverter = new StringConverter<>();
		Integer[] intElements= new Integer[]{1,2,3,4,5,6,7,8,9};
		String strIntMass = sConverter.get(intElements);
		System.out.println(strIntMass);
		FloatConverter<Double, Float> fConverter = new FloatConverter<>();
		Double dValue = fConverter.get(1.234f);
		System.out.println(dValue);
	}

}
