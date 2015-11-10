package com.nixsolutions;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by svichkar on 11/9/2015.
 */
public class Arrays {

    public static final int COUNT = 20;
    public static final int N = 10000;

    public static void main(String args[]) {

        Random random = new Random();

        int[] testArray = new int[N];
        int[] resultArrayBubble = new int[N];
        int[] resultArrayNative = new int[N];
        long start = 0;
        long finish = 0;
        long[][] timeResults = new long[2][COUNT];

        for (int i = 0; i < COUNT; i++) {

            testArray = new int[N];

            for (int j = 0; j < N; j++) {
                testArray[j] = random.nextInt(100) - 100;
            }

            start = Calendar.getInstance().getTimeInMillis();
            resultArrayBubble = Arrays.sort(testArray);
            finish = Calendar.getInstance().getTimeInMillis();
            timeResults[0][i] = getTimePeriod(start, finish);

            resultArrayNative = testArray.clone();

            start = Calendar.getInstance().getTimeInMillis();
            java.util.Arrays.sort(resultArrayNative);
            finish = Calendar.getInstance().getTimeInMillis();
            timeResults[1][i] = getTimePeriod(start, finish);
        }

        System.out.format("Bubble sort average time execution: %f milli secs.%n java.util.Arrays sort average time execution: %f milli secs.",
                getAverageValue(timeResults, 0),
                getAverageValue(timeResults, 1));
    }

    public static int[] sort(int[] array) {
        int[] sortedArray = array.clone();

        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = 0; j < sortedArray.length - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }
        return sortedArray;
    }

    private static long getTimePeriod(long start, long finish) {
        return finish - start;
    }

    private static float getAverageValue(long[][] array, int columnNumber) {

        float sum = 0;
        float everage = 0;

        for (int i = 0; i < COUNT; i++) {
            sum += array[columnNumber][i];
        }

        everage = sum / COUNT;
        return everage;
    }

}
