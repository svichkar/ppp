package com.nixsolutions;

/**
 * Created by Rybkinrolla on 25.11.2015.
 */
public class FloatToDoubleConvert implements Converter<Float,Double> {
    @Override
    public Double get(Float fnum) {
        return fnum.doubleValue();
    }
}
