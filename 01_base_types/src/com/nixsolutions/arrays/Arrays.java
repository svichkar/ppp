package com.nixsolutions.arrays;

public class Arrays {
	
	public static long startTime;
	
	// Performing sorting of array by Bubble technology
	public static int[] sort(int[] incomingArray) {
		int[] arrayForSorting = new int[incomingArray.length];
		System.arraycopy(incomingArray, 0, arrayForSorting, 0, incomingArray.length);
	//Identifying of the time before sorting is started
		startBemchmark();
		for (int i = 0; i < arrayForSorting.length; i++) {
			for (int j = 0; j < arrayForSorting.length - i - 1; j++) {
				if (arrayForSorting[j] > arrayForSorting[j + 1]) {
					int temp = arrayForSorting[j];
					arrayForSorting[j] = arrayForSorting[j + 1];
					arrayForSorting[j + 1] = temp;
				}
			}
		}
		//Counts how long took sorting
		stopBenchmark();		
		return arrayForSorting;
	}

	// Performing sorting of array be native methods of java.util.Arrays
	public static int[] nativeSorting(int[] incomingArray) {
		int[] arrayForSorting = new int[incomingArray.length];		
		System.arraycopy(incomingArray, 0, arrayForSorting, 0, incomingArray.length);
		startBemchmark();
		java.util.Arrays.sort(arrayForSorting);
		stopBenchmark();
		return arrayForSorting;
	}

	// Method for determining time right before sorting
	private static void startBemchmark() {
		startTime = System.nanoTime();
	}

	// Used for stopping time after sorting is completed
	private static void stopBenchmark() {
		startTime = System.nanoTime() - startTime;
	}

}
