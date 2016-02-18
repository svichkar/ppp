package com.nixsolutions;

public class ConverterFloatToDouble implements Converter<Double, Float> {

    @Override
    public Double get(Float i) {
        return i.doubleValue();
    }

}
