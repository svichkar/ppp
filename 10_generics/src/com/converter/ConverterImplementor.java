package com.converter;


import java.lang.reflect.Array;
import java.util.DoubleSummaryStatistics;

/**
 * Created by pantiukhin on 2/22/2016.
 */
public class ConverterImplementor<I, T> implements Converter<I, T> {
    @Override
    public T get(I i) {
        T d = null;
        String finalString;
        if (i.getClass().equals(Float.class)) {
            d = (T) Double.valueOf((Float) i);
        }
        if (i.getClass().equals(Integer[].class)) {
            Integer[] arr = (Integer[]) i;
            String s = "";
            for (int y = 0; y < arr.length; y++) {
                s += (T) String.valueOf(arr[y]) + " ";
            }
            d = (T) s;
        }
        return d;
    }
}
