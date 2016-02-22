package com.converter;

import java.lang.reflect.Array;

/**
 * Created by val on 2/21/2016.
 */

public class IntegerArrayStringConverter extends ConverterImplementor {

    public static void main(String[] args) {
        Integer[] integers = new Integer[5];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = i;
        }
        ConverterImplementor<Integer[], String> implementor = new ConverterImplementor<>();
        System.out.println(implementor.get(integers));
    }
}

