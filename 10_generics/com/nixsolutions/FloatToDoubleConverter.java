package com.nixsolutions;

public class FloatToDoubleConverter<T, I> implements Converter<Double, Float> {
	
	public Double get(Float f) {
		return f.doubleValue();
	}
}
