package com.nixsolutions;

public class ConverterTest {

	public static void main(String[] args) {
		ConvertFloatToDouble fd = new ConvertFloatToDouble();
		Double d = fd.get(1.5f);
		System.out.println("Float is converted to " + d.getClass().getName() + " with value " + d);
		ConvertIntegerToString is = new ConvertIntegerToString();
		Integer[] array = new Integer[] { 1, 2, 3, 4, 5 };
		String str = is.get(array);
		System.out.println("Integer array is converted to " + str.getClass().getName() + " with value " + str);
	}

}
