package com.nixsolutions;

import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
	countSortingTime();
    }

    public static void countSortingTime() {
	long[][] sortingTime = new long[2][20];
	Random randomNumber = new Random();
	int min = -100; // min value for Random
	int max = 100; // max value for Random
	long start = 0; // start time of sorting
	long end = 0; // end time of sorting
	long totalTimeBubbleSort = 0; // total time of sorting for bubble method
	long totalTimeJavaUtilSort = 0; // total time of sorting for method from
					// java util
	for (int i = 0; i < 20; i++) {
	    int[] initArray = new int[10000];
	    for (int j = 0; j < 10000; j++) {
		initArray[j] = randomNumber.nextInt(max - min) + min;
	    }
	    start = System.nanoTime();
	    Arrays.sort(initArray); // sort array using bubble method
	    end = System.nanoTime();
	    sortingTime[0][i] = end - start;

	    start = System.nanoTime();
	    java.util.Arrays.sort(initArray); // sort array using method from
					      // java util
	    end = System.nanoTime();
	    sortingTime[1][i] = end - start;
	    totalTimeBubbleSort = totalTimeBubbleSort + sortingTime[0][i];
	    totalTimeJavaUtilSort = totalTimeJavaUtilSort + sortingTime[1][i];
	}
	System.out.println("Average bubble sorting  time: " + totalTimeBubbleSort / 20 + " ns");
	System.out.println("Average java util method sorting time: " + totalTimeJavaUtilSort / 20 + " ns");
    }
}
