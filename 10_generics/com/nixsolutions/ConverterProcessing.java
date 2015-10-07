package com.nixsolutions;

public class ConverterProcessing {

	public static void main(String[] args) {
		FloatToDoubleConverter<Double, Float> ftdConv = new FloatToDoubleConverter<>();
		double d = ftdConv.get(3f);
		IntegerArrayToStringConverter<String, Integer[]> itsConv = new IntegerArrayToStringConverter<>();
		String temp = itsConv.get(new Integer[]{3, 4});
	}

}
