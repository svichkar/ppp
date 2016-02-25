package com.nixsolutions;

import java.util.Arrays;

/**
 * Created by sobolenko on 2/18/2016.
 */
public class IntegerToString implements Converter<Integer[],String> {
    public String get(Integer[] array) {
        return Arrays.toString(array);
    }
}
