package com.nixsolutions;

public class Arrays {
	public static void sort(int[] mas) {
		int[] sortedArr = mas.clone();
		for (int i = 0; i < sortedArr.length - 1; i++) {
			for (int j = i + 1; j < sortedArr.length; j++) {
				if (sortedArr[i] > sortedArr[j]) {
					int tmp = sortedArr[i];
					sortedArr[i] = sortedArr[j];
					sortedArr[j] = tmp;
				}
			}
		}
	}
}
