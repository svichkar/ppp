package com.nixsolutions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Random;

public class Arrays {

  public static long[][] results = new long[20][2];

  public static void main(String[] args) {

    BigDecimal bigD = converter();
    startApp();

  }

  public static void startApp() {

    final int TOTAL_ITERATIONS = 20;
    for (int i = 0; i < TOTAL_ITERATIONS; i++) {
      int[] arr = new int[10000];
      for (int j = 0; j < arr.length; j++) {

        Random rd = new Random();
        final int MAXIMUM = 100;
        final int MINIMUM = -100;
        int range = MAXIMUM - MINIMUM + 1;
        int randomNum = rd.nextInt(range) + MINIMUM;
        arr[j] = randomNum;

      }

      long startTime = System.currentTimeMillis();
      java.util.Arrays.sort(arr.clone());

      long timeForCurrentIteration = System.currentTimeMillis() - startTime;

      results[i][0] = timeForCurrentIteration;
      startTime = System.currentTimeMillis();
      int[] sortedArrayByBubbleMethod =  sort(arr);
      timeForCurrentIteration = System.currentTimeMillis() - startTime;
      results[i][1] = timeForCurrentIteration;
    }
    long avrerageTimeContainer[] = new long[2];
    final int ARR_DEPTH = 2;
    final int ARR_LENGTH = 2;
    for (int i = 0; i < ARR_DEPTH; i++) {
      for (int j = 0; j < ARR_LENGTH; j++) {
        avrerageTimeContainer[i] += results[j][i];

      }

    }
    System.out.println("Average time for Array.sort: " + avrerageTimeContainer[0] / 20 + " ms");
    System.out.println(
        "Average time for Bubble sorting method: " + avrerageTimeContainer[1] / 20 + " ms");

    System.out.println();

  }

  /**
   * Sorting method. Order - ascending
   * 
   * @return new sorted array
   */
  public static int[] sort(int[] input) {


    int[] arr = input.clone();
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int t = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = t;
        }
      }
    }
    return arr;
  }

  /**
   * Method converts double value form exponential view to general and conversely.
   * 
   * @return BigDecimal value
   */
  public static BigDecimal converter() {

    System.out.println("Enter number to convertion here : ");
    String inputString = null;
    try {
      BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
      inputString = bufferRead.readLine();

    } catch (Exception e) {
      e.printStackTrace();

    }

    BigDecimal bd = new BigDecimal(inputString);
    System.out.println(bd.doubleValue());


    return bd;
  }

}
