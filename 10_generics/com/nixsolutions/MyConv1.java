package com.nixsolutions;

/**Class implementing interface Convertor
 * 
 * @author maxb
 *
 */
public class MyConv1 implements Convertor<Float, Double> {

	@Override
	public Double get(Float f) {
		Number num = f;
		return num.doubleValue();
	}

}
