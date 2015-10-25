package com.nixsolutions;

public class FloatToDoubleConverter implements Converter<Double, Float> {
	
	public Double get(Float f) {
		return f.doubleValue();
	}
}
