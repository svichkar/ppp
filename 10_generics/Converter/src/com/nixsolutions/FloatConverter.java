package com.nixsolutions;

public class FloatConverter implements Converter<Double, Float> {

	@Override
	public Double get(Float element) {
		// TODO Auto-generated method stub
		return element.doubleValue();
	}

}
