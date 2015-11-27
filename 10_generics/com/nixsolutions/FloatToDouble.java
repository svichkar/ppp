package com.nixsolutions;

/**
 * the class has only 1 method get(Float x). Its purpose is to convert a Float
 * value to a double value
 * 
 * @author kryzhanovskiy
 *
 */
public class FloatToDouble implements Converter<Float, Double> {

	/**
	 * the method receives a float value, convert it to double and returns
	 */
	@Override
	public Double get(Float toBeconverted) {
		return toBeconverted.doubleValue();
	}

}
