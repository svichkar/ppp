package com.nixsolutions;

public class ConverterFromFloatToDouble implements Converter<Float, Double> {

	@Override
	public Double get(Float input) {
		Double res = (double) (input);
		return res;
	}

}
