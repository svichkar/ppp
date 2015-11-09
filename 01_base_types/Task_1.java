package com.nixsolutions;

import java.util.Random;

public class Task_1 {

	public static void main(String[] args) {
		
		long[][] timeArray = new long[20][2];
		long sumTimeBubble = 0L;
		long sumTimeSort = 0L;
		for (int i=0; i<20; i++){
			int[] resultArray = new int [10000];
			Random random = new Random();
			for (int j=0 ;j<resultArray.length; j++)
				resultArray[j]=random.nextInt(201)-100;
			long timerStart = System.nanoTime();
			Arrays.sort(resultArray);
			long timer = System.nanoTime()-timerStart;
			timeArray[i][0]=timer;
			sumTimeBubble+=timer;
			timerStart = System.nanoTime();
			java.util.Arrays.sort(resultArray);
			timer = System.nanoTime()-timerStart;
			timeArray[i][1]=timer;
			sumTimeSort+=timer;
			
		}
		
		System.out.println("Average time Bubble Sort = "+sumTimeBubble/timeArray.length);
		System.out.println("Average time Sort = "+sumTimeSort/timeArray.length);			
		
	}

}
