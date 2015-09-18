package com.nixsolutions;

import java.util.Random;

public class feature {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String oldArr = "";
		String sortArr = "";
		int[] randomArray = new int[10];
		for (int i = 0; i < 10; i++) {
			Random r = new Random(System.nanoTime());
			randomArray[i] = r.nextInt(201) - 100;
		}
		int[] newArr = Arrays.sort(randomArray);
		for (int x : randomArray)
			oldArr += x + "; ";
		for (int x : newArr)
			sortArr += x + "; ";
		System.out.println("new array: " + sortArr);
		System.out.println("old array:" + oldArr);

	}

}
