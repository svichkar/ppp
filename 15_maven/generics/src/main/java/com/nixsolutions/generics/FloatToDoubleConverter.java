package com.nixsolutions.generics;

public class FloatToDoubleConverter implements Converter<Double, Float> {

    @Override
    public Double get(Float input) {
	return input.doubleValue();
    }

}
