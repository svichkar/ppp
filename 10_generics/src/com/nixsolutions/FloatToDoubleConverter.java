package com.nixsolutions;

public class FloatToDoubleConverter implements Converter<Float, Double> {

    @Override
    public Float get(Double i) {

	return i.floatValue();
    }

}
