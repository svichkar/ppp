package com.nixsolutions.Arrays;

import java.util.Arrays;

public class FirstExercises {

	private static int[] massAfterSort;

	private static int[] sort(int[] massForSort) {
		massAfterSort = (int[]) massForSort.clone();

		// System.arraycopy(MassForSort, 0 ,MassAfterSort, 0,
		// MassForSort.length);

		for (int i = 0; i < massAfterSort.length; i++) {
			for (int j = 0; j < massAfterSort.length - i - 1; j++) {
				if (massAfterSort[j] > massAfterSort[j + 1]) {
					int t = massAfterSort[j];
					massAfterSort[j] = massAfterSort[j + 1];
					massAfterSort[j + 1] = t;
				}
			}
		}
		return massAfterSort;
	}

	public static void main(String[] args) {
		int[] mySortMass;
		int[] standartSortMass;
		int[] mainMass = new int[10000];
		long[][] timeForSort = new long[2][20];
		long startTime = 0L;
		long finishTime = 0L;
		long mySortTotalTime = 0L;
		long standartSortTotalTime = 0L;
		long mySortAverageTime = 0L;
		long standartSortAverageTime = 0L;
		long[] separateTimeForEverySort = new long[20];

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < mainMass.length; j++) {
				mainMass[j] = (int) ((Math.random() * (201) - 100));
			}
			startTime = System.nanoTime();
			mySortMass = FirstExercises.sort(mainMass);
			finishTime = System.nanoTime();
			timeForSort[0][i] = finishTime - startTime;

			Arrays.sort(mainMass);
			standartSortMass = mainMass;
			finishTime = System.nanoTime();
			timeForSort[1][i] = finishTime - startTime;

		}
		for (int k = 0; k < 20; k++) {
			mySortTotalTime = mySortTotalTime + timeForSort[0][k];
			separateTimeForEverySort[k] = timeForSort[0][k];

			standartSortTotalTime = standartSortTotalTime + timeForSort[1][k];
			separateTimeForEverySort[k] = timeForSort[1][k];

		}
		mySortAverageTime = mySortTotalTime / separateTimeForEverySort.length;
		standartSortAverageTime = standartSortTotalTime / separateTimeForEverySort.length;
		System.out.println("Average time for my buble sort is " + mySortAverageTime / Math.pow(10, 9) + " seconds;");
		System.out.println("");
		System.out.println(
				"Average time for standart sort is " + standartSortAverageTime / Math.pow(10, 9) + " seconds.");

	}

}
