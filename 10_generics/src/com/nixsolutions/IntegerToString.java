package com.nixsolutions;

/**
 * Created by sobolenko on 2/18/2016.
 */
public class IntegerToString implements Converter<Integer[], String> {
    public String get(Integer[] array) {
        String result = "";
        for (Integer value : array) {
            result += value.toString() + " ";
        }
        return result;
    }
}
