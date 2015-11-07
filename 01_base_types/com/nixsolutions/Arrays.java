package com.nixsolutions;

/**
 * Created by Sergey on 06.11.2015.
 */
public class Arrays {

    static long sort (int[]input) {
        int[]output=(input.clone());
        long start=System.currentTimeMillis();
        long duration;

        for (int i = 0; i <output.length ; i++) {
            for (int j = 0; j <output.length ; j++) {
                if (output[i]<output[j]){
                    output[i]=output[i]+output[j];
                    output[j]=output[i]-output[j];
                    output[i]=output[i]-output[j];
                }
            }
        }
        duration=System.currentTimeMillis()-start;
    return duration;
    }
}
