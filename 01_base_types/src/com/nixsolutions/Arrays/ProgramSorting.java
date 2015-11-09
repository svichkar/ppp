package com.nixsolutions.Arrays;

import java.util.Random;

public class ProgramSorting {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 2- dimensions array for storing sorting timings Bubble;Native
		long[][] sortingTimings = new long[2][20];
		for (int i = 0; i < 20; i++) {
			// Generates array of the random values in between -100 and 100
			int[] startArray = new int[10000];
			for (int a = 0; a < startArray.length; a++) {
				Random r = new Random(System.nanoTime());
				startArray[a] = r.nextInt(201) - 100;
			}
			// Performing of the Bubble Sorting
			Arrays.sort(startArray);
			sortingTimings[0][i] = Arrays.startTime;

			// Performing of the Native Sorting
			Arrays.nativeSorting(startArray);
			sortingTimings[1][i] = Arrays.startTime;

		}
		System.out.println("Average time of 'Bubble sorting' is: " + countAverage(sortingTimings[0]) + " nano seconds");
		System.out.println("Average time of 'java's native sorting' is: " + countAverage(sortingTimings[1]) + " nano seconds");
	}

	// Counts average value of the array of long
	private static long countAverage(long[] input) {
		long total = 0;
		for (int i = 0; i < input.length; i++) {
			total += input[i];
		}
		return total / input.length;
	}

}
