package com.nixsolutions;

public class ConverterProcessing {

	public static void main(String[] args) {
		FloatToDoubleConverter ftdConv = new FloatToDoubleConverter();
		double d = ftdConv.get(3f);
		IntegerArrayToStringConverter itsConv = new IntegerArrayToStringConverter();
		String temp = itsConv.get(new Integer[] {3, 4, 6, 18, 24});
	}

}
