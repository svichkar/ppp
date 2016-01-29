package com.nixsolutions;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import com.nixsolutions.Converter;

public class DataTypesLab {

	static final int ARR_SIZE = 10000;
	static final int ITER_NUMBER = 20;
	static final int MIN_LIMIT = -100;
	static final int MAX_LIMIT = 100;

	public static void main(String[] args) {
		long[][] arrTimeComparer = new long[ARR_SIZE][2];
		Random randNum = new Random();
		System.out.println("Task 1");
		for (int i = 0; i < ITER_NUMBER; i++) {
			int[] arr = new int[ARR_SIZE];
			for (int j = 0; j < arr.length; j++) {				
				arr[j] = randNum.nextInt(MAX_LIMIT + 1 - MIN_LIMIT) + MIN_LIMIT;
			}
			long startCustomSort = System.nanoTime();
			com.nixsolutions.Arrays.sort(arr);
			long elapsedCustomSort = System.nanoTime() - startCustomSort;
			arrTimeComparer[i][0] = elapsedCustomSort;

			long startUtilSort = System.nanoTime();
			Arrays.sort(arr);
			long elapsedUtilSort = System.nanoTime() - startUtilSort;
			arrTimeComparer[i][1] = elapsedUtilSort;
		}

		long[] colSum = new long[arrTimeComparer[0].length];
		for (int i = 0; i < arrTimeComparer.length; i++) {
			for (int j = 0; j < arrTimeComparer[i].length; j++) {
				colSum[j] += arrTimeComparer[i][j];
			}
		}
		for (int j = 0; j < colSum.length; j++) {
			colSum[j] = colSum[j] / arrTimeComparer.length;
		}
		System.out.println("Average time for custom sort and util sort accordingly: " + Arrays.toString(colSum));

		System.out.println("Task 2");
		System.out.print("Enter your number or type \'exit\' to quit: ");
		try (Scanner input = new Scanner(System.in)) {
			String line = input.nextLine();
			while (!line.equalsIgnoreCase("exit")) {
				Converter.convert(line);
				line = input.nextLine();
			}
		}
	}
}
