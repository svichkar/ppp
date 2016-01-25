package com.nixsolutions;

import java.util.Random;

/**
 * Created by sobolenko on 1/25/2016.
 */

public class Arrays {
    public static float sort(int[] arr)
    {
        int a=0;
        long startBulbTimer = System.nanoTime();
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
        long endBulbTimer = System.nanoTime();
        return (endBulbTimer - startBulbTimer)/1000000;
    }

    public static void main(String[] args)
    {
        Random random = new Random();
        long[] avgTimes = new long[20];
        int[] array = new int[10000];
        long summ = 0;
        for(int count=0;count<20;count++)
        {
            for(int i=0;i<array.length;i++)
            {
                array[i]=random.nextInt(201)-100;
            }
            avgTimes[count] = (long)sort(array);
            System.out.print(sort(array)+" ms"+"\n");//!!!!!
        }

        for(long l: avgTimes)
        {
            summ +=l;
        }
        System.out.println(summ/avgTimes.length);
        //System.out.print(java.util.Arrays.toString(array));
    }
}
