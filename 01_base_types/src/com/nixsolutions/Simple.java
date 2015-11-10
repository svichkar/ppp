package com.nixsolutions;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Simple {
	
	//private static long time;

		
		public static void main(String[] args) {
						
			Random rand = new Random();
			
			//com.nixsolutions.Arrays nameVariable = new com.nixsolutions.Arrays();
			
			int[] anArray;
			anArray = new int[10000];
			
			long times;
			
			int timeEnd;
			
			int[][] timeSort;
			timeSort = new int[2][20];
			
			for(int i=0; i<20; i++)
			{
				for(int j=0; j<10000; j++)
				{
			
				anArray[j] = -100 + rand.nextInt(200); // Random between -100 and 100
							
				}
				
				//Sort1
				
				times = startBenchmark();				
				com.nixsolutions.Arrays.sort(anArray);
				timeEnd = stopBenchmark(times);
				timeSort[0][i]=timeEnd;
												
				
				//Sort2
								
				times = startBenchmark();
				Arrays.sort(anArray);
				timeEnd = stopBenchmark(times);
				timeSort[1][i]=timeEnd;
									
			
		}
			
			for(int i=0; i<timeSort.length; i++) {
				  for(int j=0; j<timeSort[i].length; j++) {
				    System.out.print(timeSort[i][j] + "\t");
				  }
				    System.out.println();
			 		
			}
			
			
			System.out.println(IntStream.of(timeSort[0]).average());
			System.out.println(IntStream.of(timeSort[1]).average());
			
		}
			
		private static long startBenchmark() {
			long timeStart;
			timeStart = System.currentTimeMillis();
			return timeStart;
			
		}
		
		private static int stopBenchmark(long start) {
			long time;
			time = System.currentTimeMillis() - start;
			System.out.println("time="+time);
			return (int)time;
									
		}

	}


