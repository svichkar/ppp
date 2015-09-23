package task1;

import java.util.Random;

import com.nixsolutions.*;

public class Main {
	
	public static void main(String[] args) {
		
		final int ITERATION = 20;
		long [][] timeForSorting = new long [2][ITERATION];
		long customSortSumTime = 0;
		long utilSortSumTime = 0;
				
		for(int i = 0; i < ITERATION; i++){
			
			int[] arrayNotSorted = new int [10000];
			for(int j = 0; j < arrayNotSorted.length; j++){				
				arrayNotSorted[j] = randomInteger(-100, 100);
			}
			
			//run custom sort()
			long startTime = System.nanoTime();
			Arrays.sort(arrayNotSorted);
			long endTime = System.nanoTime();
			timeForSorting[0][i] = endTime - startTime;
			customSortSumTime += timeForSorting[0][i];
			
			//run java.util.Arrays
			startTime = System.nanoTime();
			java.util.Arrays.sort(arrayNotSorted);
			endTime = System.nanoTime();
			timeForSorting[1][i] = endTime - startTime;	
			utilSortSumTime += timeForSorting[1][i];	
			
		}
		
		System.out.println("Arrays.sort() average time: " + customSortSumTime/ITERATION);
		System.out.println("java.util.Arrays average time: " + utilSortSumTime/ITERATION);		
		
	}
	
	public static int randomInteger(int min, int max) {
		
	    Random rand = new Random();	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}
