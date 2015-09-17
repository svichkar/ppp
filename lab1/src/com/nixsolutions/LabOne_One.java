package com.nixsolutions;

import java.util.Random;

/**
 * Created by kulishov on 09/17/2015.
 */
public class LabOne_One {
    public static void main(String[] args){
        int[][] timeResults = new int[20][2];
        long sumCustom = 0L;
        long sumNative = 0L;
        for (int i=0;i<timeResults.length;i++){
            int[] workArr = createArray_10000();
            long startTime = System.nanoTime();
            Arrays.sort(workArr);
            long endTime = System.nanoTime() - startTime;
            timeResults[i][0] = (int) endTime;
            sumCustom += endTime;
            startTime = System.nanoTime();
            java.util.Arrays.sort(workArr);
            endTime = System.nanoTime() - startTime;
            timeResults[i][1] = (int) endTime;
            sumNative += endTime;
        }
        System.out.printf("Custom sort average time (ms): %1$d\r\n", sumCustom/timeResults.length);
        System.out.printf("Native sort average time (ms): %1$d\r\n", sumNative/timeResults.length);
    }

    private static int[] createArray_10000(){
        int[] returnArr = new int[10000];
        Random rand = new Random();
        for (int i =0;i<returnArr.length;i++){
            returnArr[i] = rand.nextInt(201)-100;
        }
        return returnArr;
    }
}
