package com.nixsolutions;

import java.util.Random;

public class Task1 {
	public static void main(String[] args){
		long[][] sortingTime = new long[2][20];
		Random randomNumber = new Random();
		int min = -100;
		int max = 100;
		long start = 0;
		long end = 0;
		for (int i = 0; i < 20; i++){
			int[] initArray = new int[10000];
			for (int j = 0; j < 10000; j++){
				initArray[j] = randomNumber.nextInt(max - min)+ min;
			}
			start = System.nanoTime();
			Arrays.sort(initArray);
			end = System.nanoTime();
			sortingTime[0][i] = end - start;
			
			start = System.nanoTime();
			java.util.Arrays.sort(initArray);
			end = System.nanoTime();
			sortingTime[1][i] = end - start;
		}
		long totalTimeBubbleSort = 0;
		long totalTimeJavaUtilSort = 0;
		for (int i = 0; i < 20; i++){
			totalTimeBubbleSort = totalTimeBubbleSort + sortingTime[0][i];
			totalTimeJavaUtilSort = totalTimeJavaUtilSort + sortingTime[1][i];
		}
		System.out.println("Average bubble sorting  time: " + totalTimeBubbleSort/20 + " ns");
		System.out.println("Average java util method sorting time: " + totalTimeJavaUtilSort/20 + " ns");
	}
}
