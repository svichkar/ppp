package com.nixsolutions;

/**
 * Created by sobolenko on 2/18/2016.
 */
public class FloatToDouble implements Converter<Float,Double>{
    public Double get(Float value) {
        return value.doubleValue();
    }
}

