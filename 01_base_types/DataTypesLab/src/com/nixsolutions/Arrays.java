package com.nixsolutions;

public class Arrays {
	
	public static int[] sort(int[] arrInitial) {
		int[] arrSorted = java.util.Arrays.copyOf(arrInitial, arrInitial.length);
		for (int i = 0; i < arrSorted.length; i++) {
			for (int j = 0; j < arrSorted.length - i - 1; j++) {
				if (arrSorted[j] > arrSorted[j + 1]) {
					int tmp = arrSorted[j];
					arrSorted[j] = arrSorted[j + 1];
					arrSorted[j + 1] = tmp;
				}
			}
		}
		return arrSorted;
	}	
}
