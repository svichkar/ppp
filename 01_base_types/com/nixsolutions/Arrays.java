// Arrays.java
package com.nixsolutions;

import java.util.Random;

/** Sorts arrays using different methods. */
public class Arrays {
  static final int LENGTH = 10000;
  static final int LOOPS = 20;
  
  /**
  * Performs sorting of integer array taken as an argument,
  * in ascending order, using non-optimized bubble sorting method.
  */
  public static void sort(int[] a){
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for(int i = 1; i < a.length; i++) {
        if (a[i-1] > a[i]) {
          int temp = a[i-1];
          a[i-1] = a[i];
          a[i] = temp;
          swapped = true;
        }
      }
    }
  }
  
  private int[] randFillArray(int arrayLength) {
    Random rand = new Random();
    int[] a = new int[arrayLength];
    for (int i = 0; i < a.length; i++) {
      a[i] = rand.nextInt(200)-100;
    }
    return a;
  }
  
  private double avg(long[] a) {
    long sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum = sum + a[i];
    }
    double avg = sum/a.length/1e9; // 1e9 == 10^9
    return avg;
  }
  
  private void print(double x, double y){
    System.out.println("Average execution times for each iteration (in seconds):\n");
    System.out.printf("com.nixsolutions.Arrays.sort: %.9f\n", x);
    System.out.printf("java.util.Arrays.sort: %.9f\n", y);
  }
  
  /**
  * Creates an array, measures the time of sorting it using
  * each of the methods, repeats the whole procedure
  * multiple times, then prints average time for each
  * method.
  */
  public static void main(String[] args) {
    Arrays arrays = new Arrays();
    long[][] results = new long[2][LOOPS];
    System.out.println("Sorting arrays " + LOOPS + " times by each of the methods...\n");
    for (int i = 0; i < LOOPS; i++) {
      int[] orig = arrays.randFillArray(LENGTH);
      int[] copy = new int[LENGTH];
      long start;
      long stop;

      System.arraycopy(orig, 0, copy, 0, LENGTH);
      start = System.nanoTime();
      arrays.sort(copy);
      stop = System.nanoTime();
      results[0][i] = stop - start;

      System.arraycopy(orig, 0, copy, 0, LENGTH);
      start = System.nanoTime();
      java.util.Arrays.sort(copy);
      stop = System.nanoTime();
      results[1][i] = stop - start;
    }
    arrays.print(arrays.avg(results[0]),
                 arrays.avg(results[1]));
  }
  
}