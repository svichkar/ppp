package com.nixsolutions;

import java.util.Random;

/**
 * Created by rybkin on 11/6/2015.
 */
public class Arrays {
    final static int quantityOfArrays = 20;
    static int[] sort(int[] initialArray) {
        int[] cloneArray = initialArray.clone();
        int arrLength = cloneArray.length; // I'm not sure about this one, but I suppose it would be quicker than call every time cloneArray.length inside loop
        for (int i = 0; i < arrLength; i++) {
            for (int j = i + 1; j < arrLength; j++) {
                if (cloneArray[i] > cloneArray[j]) {
                    int temp = cloneArray[i];
                    cloneArray[i] = cloneArray[j];
                    cloneArray[j] = temp;
                }
            }
        }
        return cloneArray;
    }

    public static void main(String[] args) {
        Random generator = new Random();
        long[][] arrayTimeToSort = new long[quantityOfArrays][2];
        long sumBubbleSort = 0;
        long sumDefaultSort = 0;
        for (int i = 0; i < quantityOfArrays; i++) {
            int[] arrayNotSorted = new int[10000];
            for (int z = 0; z < arrayNotSorted.length; z++) {
                arrayNotSorted[z] = generator.nextInt(201) - 100;
            }
            long timeStart = System.nanoTime();
            Arrays.sort(arrayNotSorted);
            arrayTimeToSort[i][0] = System.nanoTime() - timeStart;
            sumBubbleSort += arrayTimeToSort[i][0];
            timeStart = System.nanoTime();
            java.util.Arrays.sort(arrayNotSorted);
            arrayTimeToSort[i][1] = System.nanoTime() - timeStart;
            sumDefaultSort += arrayTimeToSort[i][1];

        }
        System.out.println("Average time of Bubble sort method in nanoseconds = " + sumBubbleSort / quantityOfArrays);
        System.out.println("Average time of java.util.Arrays.sort method in nanoseconds = " + sumDefaultSort / quantityOfArrays);

    }
}
