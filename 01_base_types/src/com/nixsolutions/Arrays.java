package com.nixsolutions;

import java.util.Random;

/**
 * Created by sobolenko on 1/25/2016.
 */

public class Arrays {
    public static int[] sort(int[] arr)
    {
        int a=0;
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    a=arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]=a;
                }
            }
        }
        return arr;
    }
    public static String average(double[][] time)
    {
        String result ="";
        for(double[] t: time)
        {
            long summ=0;
            for(double l: t)
            {
                summ +=l;
            }
            result += (double)summ/t.length+",";
        }
        return result;
    }
    public static void main(String[] args)
    {
        Random random = new Random();
        double[] bulbTime = new double[20];
        double[] bysTime = new double[20];
        double[][] time = new double[2][20];
        int[] arrayCopy = new int[10000];
        int[] array = new int[10000];
        String avgTime = "";
        double avgBulbTime = 0;
        double avgSysTime = 0;
        for(int count=0;count<20;count++)
        {
            for(int i=0;i<array.length;i++)
            {
                array[i]=random.nextInt(201)-100;
            }
            long startBulbTimer = System.nanoTime();
            arrayCopy = sort(java.util.Arrays.copyOf(array,array.length));
            long endBulbTimer = System.nanoTime();
            //----------------------------------
            long startTimer = System.nanoTime();
            java.util.Arrays.sort(java.util.Arrays.copyOf(arrayCopy, arrayCopy.length));
            long endTimer = System.nanoTime();
            //----------------------------------
            time[0][count] = (double)(endBulbTimer - startBulbTimer)/1000000;
            time[1][count] = (double)(endTimer - startTimer)/1000000;
        }
        avgTime = average(time);
        String[] avgMidTime = avgTime.split(",");
        avgBulbTime = Double.parseDouble(avgMidTime[0]);
        avgSysTime = Double.parseDouble(avgMidTime[1]);
        System.out.println("Bubble sorting time="+avgBulbTime+"\n"+"Sort of class 'java.util.Array' time="+avgSysTime);
    }
}
