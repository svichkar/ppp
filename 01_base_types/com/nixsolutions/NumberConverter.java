package com.nixsolutions;

public class NumberConverter {

	public static void numberConverter(String floatNumber) {

		String numberLowerCase = floatNumber.toLowerCase();

		if (numberLowerCase.contains("e")) {
			int indexE = numberLowerCase.indexOf("e");
			int indexDot = numberLowerCase.indexOf(".");
			String exponent = numberLowerCase.substring(indexE + 1);

			String res = numberLowerCase.substring(0, 1);
			res += numberLowerCase.substring(indexDot + 1, indexDot + 1 + Integer.parseInt(exponent)) + ".";
			res += numberLowerCase.substring(indexDot + 1 + Integer.parseInt(exponent), indexE);

			System.out.println("Result number is " + res);
		} else {
			int index = numberLowerCase.indexOf(".");
			System.out.println("Result number is " + numberLowerCase.substring(0, 1) + "."
					+ numberLowerCase.substring(1, numberLowerCase.length()).replace(".", "") + "e" + (index - 1));
		}
	}

}
