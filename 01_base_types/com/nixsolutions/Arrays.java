package com.nixsolutions;

/** Arrays class created for first task for "Basic types" quiz.
 * @author	kulishov */
public class Arrays {
	
	/**sort method is using bubble sort algorithm 
	 * @author	kulishov
	 * @param	intArr	Is an array of integers to be sorted. 
	 * 					It wont be changed during method execution.
	 * @return			This method will return a new instance of integer array 
	 * 					with sorted values from incoming array.*/
	public static int[] sort(int[] intArr) {
		int[] returnArr = java.util.Arrays.copyOf(intArr, intArr.length);
		for (int i = 0; i < returnArr.length; i++) {
			for (int j = i + 1; j < intArr.length; j++) {
				int temp = 0;
				if (returnArr[i] < returnArr[j]) {
					temp = returnArr[i];
					returnArr[i] = returnArr[j];
					returnArr[j] = temp;
				}
			}
		}
		return returnArr;
	}
}
