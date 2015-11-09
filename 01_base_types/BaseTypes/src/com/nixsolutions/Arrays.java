/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nixsolutions;

/**
 *
 * @author mednorcom
 */
public class Arrays {

    public static int[] sort(int[] inArray) {
        int[] outArray = inArray;
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        int temp;   //holding variable
        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (j = 0; j < outArray.length - 1; j++) {
                if (outArray[j] < outArray[j + 1]) // change to > for ascending sort
                {
                    temp = outArray[j];                //swap elements
                    outArray[j] = outArray[j + 1];
                    outArray[j + 1] = temp;
                    flag = true;              //shows a swap occurred  
                }
            }
        }
        return outArray;
    }

}
