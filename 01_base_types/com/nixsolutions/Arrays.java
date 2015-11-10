package com.nixsolutions;

public class Arrays {

	public int[] createRandomArray(int size) {
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = (int) (Math.random() * 202 - 101);
		}
		return arr;
	}

	public int[] bubbleSort(int[] arr) {

		int[] arrSorted = new int[arr.length];
		arrSorted = arr.clone();

		for (int i = arrSorted.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {

				if (arrSorted[j] > arrSorted[j + 1]) {
					int tmp = arrSorted[j];
					arrSorted[j] = arrSorted[j + 1];
					arrSorted[j + 1] = tmp;
				}
			}
		}

		return arrSorted;
	}

	public void printArray(long[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}
