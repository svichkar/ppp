/**
 * 
 */
package com.nixsolutions;

/**
 * @author mixeyes
 *
 */
public class Arrays {
	private static int[] intArray;

	public Arrays() {
	}

	public static int[] sort(int[] startArray) {
		intArray = (int[])startArray.clone();
		for (int i = 0; i < intArray.length; i++) {
			for (int j = 0; j < intArray.length - i - 1; j++) {
				if (intArray[j] > intArray[j + 1]) {
					int t = intArray[j];
					intArray[j] = intArray[j + 1];
					intArray[j + 1] = t;
				}
			}
		}

		return intArray;
	}

}
