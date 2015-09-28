package com.nixsolutions;

public class StringProcessing {

	public static void main(String[] args) {
		System.out.println("Task #1.");
		String input = "test String to check all tasks";
		System.out.printf("Input string was: %1$s%nReversed version: %2$s%n", 
				input, customReverse(input));
		System.out.println("Task #2.");
		System.out.printf("Input string was: %1$s%nAfter deletion of 3 't' symbols: %2$s%n", 
				input, removeNSymbols(input, 't', 3, true));
		System.out.printf("Now with removal from end: %1$s%n", 
				removeNSymbols(input, 't', 3, false));
		System.out.println("Task #3.");
		input = "TestwOrdtocheck";
		System.out.printf("Input string was: %1$s%nAfter deduplicating and sorting: %2$s%n", 
				input, splitAndSort(input));
	}
	
	private static String customReverse(String inputStr) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = inputStr.length() - 1; i >= 0 ; i--) {
			strBuilder.append(inputStr.charAt(i));
		}
		return strBuilder.toString();
	}
	
	private static String removeNSymbols(String inputStr,
			char removeChar, int amount, Boolean fromStart) {
		StringBuilder strBuilder = new StringBuilder();
		StringBuilder tempStr = new StringBuilder(inputStr);
		if (!fromStart) {
			tempStr = tempStr.reverse();
		}
		for (int i = 0; i < tempStr.length(); i++) {
			if (Character.toLowerCase(tempStr.charAt(i)) == 
					Character.toLowerCase(removeChar) & (amount > 0)) {
				amount--;
			} else {
				strBuilder.append(tempStr.charAt(i));
			}
		}
		return fromStart ? strBuilder.toString() : strBuilder.reverse().toString();
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
		char[] tempArr = strBuilder.toString().toCharArray();
		//do a bubble sort with Character.toLower() comparison
		java.util.Arrays.sort(tempArr);
		return new String(tempArr);
	}
}
