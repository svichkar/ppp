package com.converter;

/**
 * Created by pantiukhin on 2/22/2016.
 */
public class ConverterImplementor {
    public static void main(String[] args) {
        Integer[] integers = new Integer[5];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = i;
        }
        FloatDoubleConverter<Float, Double> fdconverter = new FloatDoubleConverter<>();
        IntegerArrayStringConverter<Integer[], String> iasconverter = new IntegerArrayStringConverter<>();
        System.out.println(fdconverter.get(new Float(45f)));
        System.out.println(iasconverter.get(integers));
    }
}
