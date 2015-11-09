package com.nixsolutions;

import java.util.Random;

/**
 * Created by Sergey on 06.11.2015.
 */
public class SortComparing {
    public static void main(String[] args) {

        Random rand = new Random();
        int[] array = new int[10000];
        int[] sortedArray;
        double[][] traceTime = new double[20][2];
        long start, duration;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j] = rand.nextInt(201) - 100;
            }
            start = System.currentTimeMillis();
            com.nixsolutions.Arrays.sort(array);
            duration = System.currentTimeMillis() - start;
            traceTime[i][0] = (double) duration;

            sortedArray = (array.clone());
            start = System.currentTimeMillis();
            java.util.Arrays.sort(sortedArray);
            duration = System.currentTimeMillis() - start;
            traceTime[i][1] = (double) duration;
        }
        double sumForNix = 0.0d;
        double sumForJava = 0.0d;

        for (int i = 0; i < traceTime.length; i++) {
            sumForNix += traceTime[i][0];
            sumForJava += traceTime[i][1];
        }
        System.out.println("sorted time for nix " + sumForNix / 20);
        System.out.println("sorted time for java " + sumForJava / 20);
    }
}
