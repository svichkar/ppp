package com.nixsolutions;

public class ConvertersTest {

	public static void main(String[] args) {
		Integer[] arrIn = { 2, 5, 7, 23, 654 };
		Converter<Integer[], String> convArr = new ArrIntToString();
		System.out.println("result of the Array to String conversion: "
				+ convArr.get(arrIn));

		Float fl = 34f;
		Converter<Float, Double> convFtoD = new FloatToDouble();
		System.out.println("result of the Float to Double conversion: "
				+ convFtoD.get(fl));

	}
}
