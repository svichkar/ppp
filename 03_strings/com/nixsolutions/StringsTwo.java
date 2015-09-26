package com.nixsolutions;

public class StringsTwo {

	public static void main(String[] args) {
		String input = "test String to check all tasks";
		System.out.printf("Input string was: %1$s%nAfter deletion of 3 't' symbols: %2$s%n", 
				input, removeNSymbols(input, 't', 3, true));
		System.out.printf("Now with removal from end: %1$s%n", 
				removeNSymbols(input, 't', 3, false));
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
}
