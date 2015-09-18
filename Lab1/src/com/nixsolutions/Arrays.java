package com.nixsolutions;

public class Arrays {

	/** Bubble sorting method. Sort order - ascending */
	public static void sort(int[] a){
		int[] sortedArray = a.clone();
		for (int i = 0; i < sortedArray.length - 1; i++){
			for(int j = 0; j < sortedArray.length - 1 - i; j++){
				if (sortedArray[j]>sortedArray[j+1]){
					int b = sortedArray[j];
					sortedArray[j] = sortedArray[j+1];
					sortedArray[j+1] = b;
				}
			}
		}
	}
}
