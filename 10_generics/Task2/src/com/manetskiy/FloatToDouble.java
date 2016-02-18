package com.manetskiy;


public class FloatToDouble implements Converter<Float, Double> {

    @Override
    public Double get(Float f) {
        return f.doubleValue();
    }
}
