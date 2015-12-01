package com.nixsolutions;

public class ConvertFloatToDouble implements Converter<Double, Float> {

	@Override
	public Double get(Float i) {
		return Double.valueOf(i);
	}

}
