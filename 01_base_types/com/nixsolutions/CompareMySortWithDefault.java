package com.nixsolutions;

import java.lang.Math;
import java.util.Collections;

public class CompareMySortWithDefault {

    static final int REPEAT = 20;
    static final int SIZE_ARRAY = 10_000;
    static final int minBorder = -100;
    static final int maxBorder = 100;

    public static void main(String[] argc) {
        long[][] timeExecute = new long[REPEAT][2];

        for (int i = 0; i < REPEAT; i++) {
            int[] randomArray = new int[SIZE_ARRAY];
            for (int j = 0; j < SIZE_ARRAY; j++) {
                //Formula: Min + (int)(Math.random() * ((Max - Min) + 1))
                randomArray[j] = minBorder + (int)(Math.random() * ((maxBorder - minBorder) + 1));
            }

            //calculate time of execute
            //custom Sort
            long startTime = System.nanoTime();
            Arrays.sort(randomArray);
            long endTime = System.nanoTime();
            timeExecute[i][0] = (endTime - startTime);
            //java.util.Arrays sort
            startTime = System.nanoTime();
            java.util.Arrays.sort(randomArray);
            endTime = System.nanoTime();
            timeExecute[i][1] = (endTime - startTime);
        }

        // calculate average values
        long sumCustom = 0, sumUtil = 0;
        for (long[] val: timeExecute) {
            sumCustom += val[0];
            sumUtil += val[1];
        }
        System.out.println("Average time in the custom sort(): " + sumCustom/REPEAT + " nanosecond");
        System.out.println("Average time in the java.util.Arrays.sort(): " + sumUtil/REPEAT + " nanosecond");
    }

}
