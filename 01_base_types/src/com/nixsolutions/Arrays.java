package com.nixsolutions;

public class Arrays {

    /**
     * Bubble sorting method. Sort order - ascending
     * 
     * @param inputArray
     *            is array for sorting
     * @return sorted array
     */
    public static int[] sort(int[] inputArray) {
	int[] sortArray = inputArray.clone();
	for (int i = 0; i < sortArray.length - 1; i++) {
	    for (int j = 0; j < sortArray.length - 1 - i; j++) {
		if (sortArray[j] > sortArray[j + 1]) {
		    int b = sortArray[j];
		    sortArray[j] = sortArray[j + 1];
		    sortArray[j + 1] = b;
		}
	    }
	}
	return sortArray;
    }
}
