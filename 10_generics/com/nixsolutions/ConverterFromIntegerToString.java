package com.nixsolutions;

public class ConverterFromIntegerToString implements Converter<Integer[], String> {

	@Override
	public String get(Integer[] input) {
		String res = "";
		int size = input.length;
		for (int i = 0; i < size; i++) {
			res += input[i] + " ";
		}
		return res;
	}

}
