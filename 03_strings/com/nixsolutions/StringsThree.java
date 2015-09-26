package com.nixsolutions;

public class StringsThree {

	public static void main(String[] args) {
		String input = "TestwOrdtocheck";
		System.out.printf("Input string was: %1$s%nAfter deduplicating and sorting: %2$s%n", 
				input, splitAndSort(input));
	}
	
	private static String splitAndSort(String inputStr) {
		String[] charArr = inputStr.split("");
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < charArr.length; i++) {
			if (!strBuilder.toString().toLowerCase().
					contains(charArr[i].toLowerCase())) {
				strBuilder.append(charArr[i]);
			}
		}
		String[] tempArr = strBuilder.toString().split("");
		java.util.Arrays.sort(tempArr);
		return String.join("", tempArr);
	}
}
