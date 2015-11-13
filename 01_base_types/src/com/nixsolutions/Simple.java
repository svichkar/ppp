package com.nixsolutions;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Simple {

	public static void main(String[] args) {

		Random rand = new Random();

		int[] anArray;
		anArray = new int[10000];

		long timeStart;
		long timeEnd;

		int[][] timeSort;
		timeSort = new int[2][20];

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10000; j++) {

				anArray[j] = -100 + rand.nextInt(200); // Random between -100
														// and 100
			}

			// Sort1

			timeStart = System.currentTimeMillis();
			com.nixsolutions.Arrays.sort(anArray);
			timeEnd = System.currentTimeMillis() - timeStart;
			timeSort[0][i] = (int) timeEnd;

			// Sort2

			timeStart = System.currentTimeMillis();
			Arrays.sort(anArray);
			timeEnd = System.currentTimeMillis() - timeStart;
			timeSort[1][i] = (int) timeEnd;

		}

		for (int i = 0; i < timeSort.length; i++) {
			for (int j = 0; j < timeSort[i].length; j++) {
				System.out.print(timeSort[i][j] + "\t");
			}
			System.out.println();

		}

		System.out.println(IntStream.of(timeSort[0]).average());
		System.out.println(IntStream.of(timeSort[1]).average());

	}

}
