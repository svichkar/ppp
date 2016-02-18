package com.nixsolutions;

/**
 * Created by sobolenko on 2/18/2016.
 */
public class IntegerToString implements Converter<String,Integer> {
    public String get(Integer value) {
        return value.toString();
    }
}
