package com.nixsolutions;

public class Arrays {

    public static int[] sort(int[] inputArray){
        int[] outArray = (int[]) inputArray.clone();
        for(int counter1 = 0; counter1 < outArray.length; counter1++){
         for(int counter2 = 0; counter2 < outArray.length - counter1 - 1; counter2++){
             if (outArray[counter2] > outArray[counter2 + 1]) {
                 int temporary = outArray[counter2];
                 outArray[counter2] = outArray[counter2 + 1];
                 outArray[counter2 + 1] = temporary;
             }
         }
        }
        return outArray;
    }

}
