package com.nixsolutions;

public class IntArrayToString implements Converter<Integer[], String> {

	@Override
	public String get(Integer[] input) {
		String[] strings = new String[input.length];
		for (int i = 0; i < input.length; i++) {
			strings[i] = input[i].toString();
		}
		return String.join(" ", strings);
	}
}
