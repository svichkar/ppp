package com.nixsolutions;

import java.util.Random;


public class Arrays {

	//static int[] example = {157,545,8787,14,0,15,698,48,544,78};
	public static long[][] results = new long[20][2];
	
	
	public static void main(String[] args) {

		for(int i = 0; i<20;i++){
			int[] arr = new int[10000];
			for(int j =0 ; j< arr.length;j++)
			{
				
				Random rd = new Random();
				int maximum = 100;
				int minimum = -100;
				int range = maximum - minimum + 1;
				int  randomNum =  rd.nextInt(range) + minimum;
				arr[j] = randomNum;
				
			}
			
			long startTime= System.currentTimeMillis();
			java.util.Arrays.sort(arr.clone());
				
				
			long timeForCurrentIteration= System.currentTimeMillis() - startTime;
			
			results[i][0] = timeForCurrentIteration;
			startTime= System.currentTimeMillis();
			sort(arr.clone());	
			timeForCurrentIteration= System.currentTimeMillis() - startTime;
			results[i][1] = timeForCurrentIteration;
		}
		long avrerageTimeContainer[] = new long[2];
		for(int i = 0; i < 2; i++){
			    for(int j =0 ; j < 20; j ++){
			    	avrerageTimeContainer[i] +=results[j][i];
				
			}
			
		}
		System.out.println("Average time for Array.sort: "+ avrerageTimeContainer[0]/20 + " ms");
		System.out.println("Average time for Bubble sorting method: "+ avrerageTimeContainer[1]/20 + " ms");
		
		System.out.println();
		
	}
	
	public static void sort(int[] arr ){
		 for (int i = 0; i < arr.length; i++) {
	            for (int j = 0; j < arr.length - i - 1; j++) {
	                if (arr[j] > arr[j + 1]) {
	                    int t = arr[j];
	                    arr[j] = arr[j + 1];
	                    arr[j + 1] = t;
	            }
	        }
	    }
			
		
	}

}
