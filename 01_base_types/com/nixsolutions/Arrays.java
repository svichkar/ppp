package com.nixsolutions;

/**
 * Created by Sergey on 06.11.2015.
 */
public class Arrays {

    static int[] sort(int[] input) {
        int[] output = (input.clone());

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output.length; j++) {
                if (output[i] < output[j]) {
                    output[i] = output[i] + output[j];
                    output[j] = output[i] - output[j];
                    output[i] = output[i] - output[j];
                }
            }
        }
        return output;
    }
}
