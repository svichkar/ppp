package com.nixsolutions;

/**
 * Created by sobolenko on 2/18/2016.
 */
public class FloatToDouble implements Converter<Double,Float>{
    public Double get(Float value) {
        return value.doubleValue();
    }
}

