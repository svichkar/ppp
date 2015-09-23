package com.nixsolutions;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class CheckOurSortMethod {

	public static void main(String[] args) {

		// /define range for random data
		int min = -100;
		int max = 100;
		// /define amount of attempts
		int amountOfAttampts = 20;
		int arraySize = 10000;
		// /result array
		long[][] resultData = new long[2][amountOfAttampts];
		// result average for my
		double averageTimeForMy;
		double averageTimeForNative;
		// loop for amount of attempts
		for (int attempts = 0; attempts < amountOfAttampts; attempts++) {
			// /fill array by data
			int[] dataForSorting = new int[arraySize];
			for (int idx = 0; idx < arraySize; idx++) {
				dataForSorting[idx] = nextInt(min, max);
			}
			// get time for processing by our method
			long startTimeMyMethod = System.nanoTime();
			int[] sortedArray = com.nixsolutions.Arrays.sort(dataForSorting);
			long stopTimeMyMethod = System.nanoTime();
			long timeTakenByMy = stopTimeMyMethod - startTimeMyMethod;

			// get time for processing by native method
			long startTime = System.nanoTime();
			java.util.Arrays.sort(dataForSorting);
			long stopTime = System.nanoTime();
			long timeTakenByNative = stopTime - startTime;

			// /add result to double size array
			resultData[0][attempts] = timeTakenByMy;
			resultData[1][attempts] = timeTakenByNative;

			// /display result in console
			System.out.println(String.format(
					"Iteration %1s. Time for sorting: %2s vs %3s ns", attempts,
					timeTakenByMy, timeTakenByNative));
		}
		// /display average value
		averageTimeForMy = getAverage(resultData[0]);
		averageTimeForNative = getAverage(resultData[1]);
		//
		System.out.println("--------------------------");
		System.out.println(String.format(
				"Average value for my method: %.2f ns", averageTimeForMy));
		System.out.println(String.format(
				"Average value for native method: %.2f ns",
				averageTimeForNative));
	}

	public static int nextInt(int min, int max) {
		Random rnd = new Random();
		int diff = max - min;
		if (diff >= 0 && diff != Integer.MAX_VALUE) {
			return (min + rnd.nextInt(diff + 1));
		}
		// if wrong range
		int i;
		do {
			i = rnd.nextInt();
		} while (i < min || i > max);
		return i;
	}

	public static double getAverage(long[] arrayValues) {
		long sum = 0;
		for (int i = 0; i < arrayValues.length; i++) {
			sum = sum + arrayValues[i];
		}
		return Math.round(sum / arrayValues.length);
	}
}
