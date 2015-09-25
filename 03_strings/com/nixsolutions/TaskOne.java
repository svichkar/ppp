package com.nixsolutions;

public class TaskOne {
	
	public static void main(String[] args) {
		String input = "test String to check all tasks";
		System.out.printf("Input string was: %1$s%nReversed version: %2$s%n", 
				input, customReverse(input));
	}
	
	public static String customReverse(String inputStr) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = inputStr.length() - 1; i >= 0 ; i--) {
			strBuilder.append(inputStr.charAt(i));
		}
		return strBuilder.toString();
	}
}
