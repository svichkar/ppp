package com.nixsolutions;

public class ConvertIntegerToString implements Converter<String, Integer[]> {

	@Override
	public String get(Integer[] intArray) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < intArray.length; i++)
			str.append(intArray[i]).append(" ");
		return str.toString().trim();
	}

}
