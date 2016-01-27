package com.nixsolutions;

import java.util.Random;

/*
 * Created by pantiukhin on 1/25/2016.
 * Subject: working with basic datatypes
 */
public class Arrays {
    public static void main(String[] args) {
        Arrays arr = new Arrays();
        arr.startSorting();
    }

    public static void sort(int[] arrayToBeSorted) {
        int b;
        for (int i = 0; i < arrayToBeSorted.length - 1; i++)
            for (int j = 0; j < arrayToBeSorted.length - i - 1; j++)
                if (arrayToBeSorted[j] > arrayToBeSorted[j + 1]) {
                    b = arrayToBeSorted[j];
                    arrayToBeSorted[j] = arrayToBeSorted[j + 1];
                    arrayToBeSorted[j + 1] = b;
                }
    }

    public void startSorting() {
        int[] arrToBeSorted;
        long[][] timeElapsed = new long[20][2];
        long startTime, endTime, difference;
        for (int i = 0; i < 20; i++) {
            arrToBeSorted = new int[10000];
            for (int y = 0; y < arrToBeSorted.length; y++) {
                arrToBeSorted[y] = getRandomNumber();
            }
            displayArrays(arrToBeSorted, "Before Sorting: ");
            startTime = System.currentTimeMillis();
            Arrays.sort(arrToBeSorted);
            endTime = System.currentTimeMillis();
            difference = endTime - startTime;
            timeElapsed[i][0] = difference;
            displayArrays(arrToBeSorted, "After Sorting (com.nixsolutions.Arrays): ");
            startTime = System.currentTimeMillis();
            java.util.Arrays.sort(arrToBeSorted);
            endTime = System.currentTimeMillis();
            difference = endTime - startTime;
            timeElapsed[i][1] = difference;
            displayArrays(arrToBeSorted, "After Sorting (java.util.Arrays): ");
            displayTimeDifferenceArray(timeElapsed, i);
        }
        displayMeanValues(timeElapsed);
    }

    public void displayArrays(int[] arrToBeSorted, String message) {
        System.out.println("========================");
        System.out.println(message);
        for (int el : arrToBeSorted)
            System.out.print(el + " ");
        System.out.println("========================");
    }

    public int getRandomNumber() {
        int high = 100;
        int low = -100;
        Random random = new Random();
        int num = (random.nextInt(high - low) + low);
        return num;
    }

    public void displayTimeDifferenceArray(long[][] arr, int i) {
        System.out.println("========================");
        System.out.println("Iteration " + (i + 1));
        System.out.println("Sorting with the com.nixsolutions.Arrays method took " + arr[i][0] + " millisecond(s)");
        System.out.println("Sorting with the java.util.Arrays method took " + arr[i][1] + " millisecond(s)");
    }

    public void displayMeanValues(long[][] arr) {
        long nixSortMean = 0;
        long arrSortMean = 0;
        System.out.println("========================");
        System.out.println("The values for the nixsolutions sort: ");
        for (int i = 0; i < 20; i++)
            System.out.print(arr[i][0] + " ");
        System.out.println();
        System.out.println("The values for the Arrays sort: ");
        for (int i = 0; i < 20; i++)
            System.out.print(arr[i][1] + " ");
        for (int i = 0; i < 20; i++) {
            nixSortMean += arr[i][0];
            arrSortMean += arr[i][1];
        }
        System.out.println();
        System.out.println("The mean time for the nixsolutions sort: " + (double) nixSortMean / 20);
        System.out.println("The mean time for the Arrays sort: " + (double) arrSortMean / 20);
    }
}
