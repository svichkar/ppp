package com.nixsolutions;

public class StringConverter implements Converter<String, Integer[]> {

	@Override
	public String get(Integer[] element) {
		String strElem = "";

		for (int i = 0; i < element.length; i++) {
			strElem += element[i].toString() + " ";
		}
		return strElem;
	}

}
