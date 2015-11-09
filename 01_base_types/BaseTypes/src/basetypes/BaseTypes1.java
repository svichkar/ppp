/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basetypes;

import java.util.Random;

/**
 *
 * @author mednorcom
 */
public class BaseTypes1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int arraySize = 10000;
        int iterationsCount = 20;
        int iteration = 0;
        long[][] timeLog = new long[2][iterationsCount];

        while (iteration < iterationsCount) {
            //create random array
            System.out.printf("%d of %d\n", iteration+1, iterationsCount);
            Random randInt = new Random();
            int[] integers = new int[arraySize];
            for (int i = 0; i < integers.length; i++) {
                integers[i] = randInt.nextInt(200)-100;
            }

            long startime = System.nanoTime();
            int[] integersSorted = 
                    com.nixsolutions.Arrays.sort(integers); //perform custom sort   
            timeLog[0][iteration] = System.nanoTime() - startime;
            startime = System.nanoTime();
            java.util.Arrays.sort(integers); //perform default sort 
            timeLog[1][iteration] = System.nanoTime() - startime;
            iteration++;
            
        }
        long avgCustomSortTime = 0;
        long avgDefaultSortTime = 0;
        for (int i = 0; i < iterationsCount-1; i++) {
            if (i == 0) {
                avgCustomSortTime = timeLog[0][i];
                avgDefaultSortTime = timeLog[1][i];
            } else {
                avgCustomSortTime = (avgCustomSortTime*i+timeLog[0][i+1])/(i+1);
                avgDefaultSortTime = (avgDefaultSortTime*i+timeLog[1][i+1])/(i+1);
            }
        }
       
        System.out.printf("Custom (com.nixsolutions) sort takes %dns in average\n"
                + "Default (java.utils) sort takes %dns in average\n",
                avgCustomSortTime,avgDefaultSortTime);

    }

}
