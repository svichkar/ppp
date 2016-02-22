package com.converter;

/**
 * Created by val on 2/21/2016.
 */
public class FloatDoubleConverter extends ConverterImplementor {

    public static void main(String[] args) {
        ConverterImplementor<Float, Double> cimpl = new ConverterImplementor<Float, Double>();
        Double returnValue = cimpl.get(new Float(43f));
        System.out.println(returnValue);
    }
}
