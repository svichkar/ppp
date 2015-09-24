import java.util.Random;

import com.nixsolutions.Arrays;

public class Task1 {

	final static int countOfArrays = 20;
	final static int sizeOfArray = 10000;

	public static void main(String[] args) {
		Random ran = new Random();
		long[][] times = new long[2][countOfArrays];
		for (int i = 0; i < countOfArrays; i++) {
			int[] inputArrays = new int[sizeOfArray];
			int[] sortedArray = new int[sizeOfArray];
			for (int j = 0; j < inputArrays.length; j++) {
				inputArrays[j] = ran.nextInt(201) - 100;
			}
			long start = System.nanoTime();
			sortedArray = Arrays.sort(inputArrays);
			times[0][i] = System.nanoTime() - start;
			start = System.nanoTime();
			java.util.Arrays.sort(inputArrays);
			times[1][i] = System.nanoTime() - start;
		}
		long sumNixTime = 0;
		long sumJavaTime = 0;
		for (int i = 0; i < countOfArrays; i++) {
			sumNixTime += times[0][i];
			sumJavaTime += times[1][i];
		}
		System.out.println("Time of sorting by 'com.nixsolutions.Arrays.sort' method: " + sumNixTime / countOfArrays + "ns");
		System.out.println("Time of sorting by 'java.util.Arrays.sort' method: " + sumJavaTime / countOfArrays + "ns");
	}
}
