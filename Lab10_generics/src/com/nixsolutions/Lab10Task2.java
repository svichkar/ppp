package com.nixsolutions;

public class Lab10Task2 {

	public static void main(String[] args) {
		Float f = new Float(14009.35);
		Integer[] array = { 10, 9, 8, 45, 2, 7, 12, 0 };
		
		Converter<Float, Double> toDouble = new FloatToDouble();
		System.out.println("Float to Double: " + toDouble.get(f));

		IntArrayToString arrayToString = new IntArrayToString();
		System.out.println("Integers to string: " + arrayToString.get(array));
	}
}
