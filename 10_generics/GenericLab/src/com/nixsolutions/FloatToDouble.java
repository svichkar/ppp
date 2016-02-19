package com.nixsolutions;

public class FloatToDouble implements Converter<Float, Double> {

	@Override
	public Double get(Float value) {
		return (double) value;
	}

}
