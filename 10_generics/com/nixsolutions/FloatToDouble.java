package com.nixsolutions;

public class FloatToDouble implements Converter<Double, Float> {

    @Override
    public Double get(Float a) {
        return a.doubleValue();
    }
}
