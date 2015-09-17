package com.nixsolutions;

import java.util.Random;

public class Arrays {

	//static int[] example = {157,545,8787,14,0,15,698,48,544,78};
	private static int [] exs;
	
	
	public static void main(String[] args) {

		for(int i =0; i<20;i++){
			int[] arr = new int[10000];
			for(int j =0 ; j< arr.length;j++)
			{
				Random rd = new Random();
				int maximum = 100;
				int minimum = -100;
				int range = maximum - minimum + 1;
				
				int randomNum =  rd.nextInt(range) + minimum;
				
				arr[j] = randomNum;
			}
			
		}
	}
	public static int[] sort(int[] input ){
		int[] arr  = new int[input.length];
		arr = input;
		 for (int i = 0; i < arr.length; i++) {
	            for (int j = 0; j < arr.length - i - 1; j++) {
	                if (arr[j] > arr[j + 1]) {
	                    int t = arr[j];
	                    arr[j] = arr[j + 1];
	                    arr[j + 1] = t;
	            }
	        }
	    }
		
		return arr;
		
	}

}
