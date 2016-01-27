package com.nixsolutions;

public class Converter {

	public static void convert(String valueToConvert) {
		try {
			double valueResult = Double.parseDouble(valueToConvert.replace(',', '.'));
			if (valueToConvert.toUpperCase().contains("E")) {
				System.out.printf("Converted value: %f%n", valueResult);
			} else {
				System.out.printf("Converted value: %e%n", valueResult);
			}
		} catch (NumberFormatException e) {
			System.out.printf("Input value \'%s\' is in incorrect format. Please, use floating-point numbers only.%n", valueToConvert);
		}
	}
}
