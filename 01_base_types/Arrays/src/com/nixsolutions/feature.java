package com.nixsolutions;

import java.util.Random;

public class feature {

	public static void main(String[] args) throws InterruptedException {
		long[][] timeToSort = new long[2][20];
		// cycle of the 20 iterations
		for (int y = 0; y < 20; y++) {
			String oldArr = "";
			String sortArr = "";
			int[] randomArray = new int[10000];
			//generate array[10000] of random values
			for (int i = 0; i < 10000; i++) {
				Random r = new Random(System.nanoTime());
				randomArray[i] = r.nextInt(201) - 100;
			}
			//custom sorting
			long startTime = System.nanoTime();
			int[] newArr = Arrays.sort(randomArray);
			long finishTime = System.nanoTime();
			timeToSort[0][y] = finishTime - startTime;

			//system sorting
			startTime = System.nanoTime();
			java.util.Arrays.sort(randomArray);
			finishTime = System.nanoTime();
			timeToSort[1][y] = finishTime - startTime;
		}
		//calculation of the arithmetic mean
		long halfTimeToBubleSort = 0;
		long halfTimeToSystemSort = 0;
		for (int i = 0; i < 20; i++)
			halfTimeToBubleSort += timeToSort[0][i];
		halfTimeToBubleSort /= 20;
		for (int i = 0; i < 20; i++)
			halfTimeToSystemSort += timeToSort[1][i];
		halfTimeToSystemSort /= 20;
		//print of the results
		System.out.println("buble sort: " + halfTimeToBubleSort + " ns");
		System.out.println("system sort: " + halfTimeToSystemSort+ " ns");

	}

}
