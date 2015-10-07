package com.nixsolutions;

public class IntegerArrayToStringConverter<T, I> implements Converter<String, Integer[]> {
	
	public String get(Integer[] intArr) {
		StringBuilder sb = new StringBuilder();
		int len = intArr.length;
		for (int i = 0; i < len; i++) {
			sb.append(intArr[i]);
			sb.append(" ");
		}
		return sb.toString().trim();
	}
}
