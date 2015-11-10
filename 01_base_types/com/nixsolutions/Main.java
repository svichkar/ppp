package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		Arrays arrObj = new Arrays();
		int loopsCount = 20;
		long timeStart = 0L;
		long timeEnd = 0L;
		long[][] arrTimeMeasurements = new long[2][loopsCount];
		long averageTimeBublbeSort = 0L;
		long averageTimeStandardSort = 0L;

		for (int i = 0; i < loopsCount; i++) {
			int[] arr = arrObj.createRandomArray(10000);

			timeStart = System.nanoTime();
			int[] resBubble = arrObj.bubbleSort(arr);
			timeEnd = System.nanoTime();
			// for (int j : resBubble) {
			// System.out.print(j + ", ");
			// }
			arrTimeMeasurements[0][i] = timeEnd - timeStart;

			timeStart = System.nanoTime();
			java.util.Arrays.sort(arr);
			timeEnd = System.nanoTime();
			// for (int j : arr) {
			// System.out.print(j + ", ");
			// }
			arrTimeMeasurements[1][i] = timeEnd - timeStart;
		}

		for (int i = 0; i < loopsCount; i++) {
			averageTimeBublbeSort += arrTimeMeasurements[0][i];

			averageTimeStandardSort += arrTimeMeasurements[1][i];
		}

		// arrObj.printArray(arrTimeMeasurements);

		System.out
				.println("Average time of bubble sort is " + (long) (averageTimeBublbeSort / loopsCount) + " nanosec");
		System.out.println(
				"Average time of standard sort is " + (long) (averageTimeStandardSort / loopsCount) + " nanosec");
		System.out.println();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String value = "";
		System.out.print("Please enter a float number: ");
		value = br.readLine();
		NumberConverter.numberConverter(value);

	}
}
