package com.nixsolutions;

public class SimpleClass {

	@ToString
	private Object str1;

	private String str2;

	@ToString
	private int number;

	public SimpleClass(Object str1, String str2, int number) {
		this.str1 = str1;
		this.str2 = str2;
		this.number = number;
	}

	@Override
	public String toString() {
		String strRes = "str1: " + String.valueOf(str1) + "; ";
		strRes += "str2: " + String.valueOf(str2) + "; ";
		strRes += "number: " + String.valueOf(number) + ".";
		return strRes;
	}
}
