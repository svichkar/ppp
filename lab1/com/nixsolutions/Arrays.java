package com.nixsolutions;

public class Arrays {

	public static int[] sort(int[] inputData) {

		int[] resultArray = new int[] {};
		// fill tempArray with data
		resultArray = inputData.clone();
		// sort it out
		for (int idx0 = 0; idx0 < resultArray.length; idx0++) {
			for (int idx1 = 0; idx1 < resultArray.length - idx0 - 1; idx1++) {
				if (resultArray[idx1] > resultArray[idx1 + 1]) {
					// temp variable
					int tempVal = resultArray[idx1];
					resultArray[idx1] = resultArray[idx1 + 1];
					resultArray[idx1 + 1] = tempVal;
				}
			}
		}

		return resultArray;
	}
}
