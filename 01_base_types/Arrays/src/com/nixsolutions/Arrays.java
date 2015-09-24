/**
 * 
 */
package com.nixsolutions;

/**
 * class implements static method for sorting of int arrays by bubble method
 * 
 * @author mixeyes
 */
public class Arrays {
	private static int[] intArray;

	/**
	 * void constructor
	 */
	public Arrays() {
	}

	/**
	 * method for sorting of int arrays by bubble method
	 * 
	 * @param startArray
	 *            int array
	 * @return sorted int array
	 */
	public static int[] sort(int[] startArray) {
		intArray = (int[]) startArray.clone();
		for (int i = 0; i < intArray.length; i++) {
			for (int j = 0; j < intArray.length - i - 1; j++) {
				if (intArray[j] > intArray[j + 1]) {
					// save max value to t variable
					int t = intArray[j];
					// exchange of places
					intArray[j] = intArray[j + 1];
					intArray[j + 1] = t;
				}
			}
		}

		return intArray;
	}

}
