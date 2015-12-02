package com.nixsolutions;

/**
 * The Class FloatToDouble.
 */
public class FloatToDouble implements Conventer<Float, Double>{

	/* (non-Javadoc)
	 * @see com.nixsolutions.Conventer#get(java.lang.Object)
	 */
	@Override
	public Double get(Float data) {
		double result = (double)data;
		return result;
	}

}
