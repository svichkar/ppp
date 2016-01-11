package com.nixsolutions;

import java.util.Random;

public final class Arrays {

	public static void main(String[] args) {

		long[][] sortTime = new long[2][20];

		for (int x = 0; x < 20; x++) {
			int[] arrCheck = Arrays.generateArr();
			sortTime[0][x] = checkTimeBuble(arrCheck);
			sortTime[1][x] = checkTimeQsort(arrCheck);
		}

		String result = resultAvg(sortTime);
		System.out.println(result);

	}

	public static int[] generateArr() {
		int min = -100;
		int max = 100;
		Random rand = new Random();

		int[] origArr = new int[10000];
		for (int i = 0; i < 10000; i++) {
			int randomNum = rand.nextInt((max - min) + 1) + min;
			origArr[i] = randomNum;
		}
		return origArr;
	}

	public static int[] sort(int[] array) {

		int[] sortedArray = java.util.Arrays.copyOf(array, array.length);

		for (int i = 0; i < sortedArray.length; i++) {
			for (int j = 0; j < sortedArray.length - i - 1; j++) {
				if (sortedArray[j] > sortedArray[j + 1]) {
					int temp = sortedArray[j];
					sortedArray[j] = sortedArray[j + 1];
					sortedArray[j + 1] = temp;
				}
			}
		}
		return sortedArray;
	}

	public static long checkTimeBuble(int[] array) {

		long start;
		long stop;
		long total;

		int[] sortedArray = java.util.Arrays.copyOf(array, array.length);
		start = System.nanoTime();

		sort(sortedArray);

		stop = System.nanoTime();
		total = stop - start;

		return total;
	}

	public static long checkTimeQsort(int[] array) {

		long start;
		long stop;
		long total;

		int[] sortedArray = java.util.Arrays.copyOf(array, array.length);
		start = System.nanoTime();

		java.util.Arrays.sort(sortedArray);

		stop = System.nanoTime();
		total = stop - start;

		return total;
	}

	public static String resultAvg(long[][] array) {
		long bubbAvg;
		long qsortAvg;
		long bubbTotal = 0;
		long qsortTotal = 0;

		for (int i = 0; i < array.length; i++) {
			bubbTotal += array[0][i];
			qsortTotal += array[1][i];
		}
		bubbAvg = bubbTotal / array.length;
		qsortAvg = qsortTotal / array.length;

		return "bubble average (ns) = " + bubbAvg + "\n" + "qsort average (ns) = " + qsortAvg;
	}
}
