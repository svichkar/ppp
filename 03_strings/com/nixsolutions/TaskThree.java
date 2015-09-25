package com.nixsolutions;

public class TaskThree {

	public static void main(String[] args) {
		String input = "testwordtocheck";
		System.out.printf("Input string was: %1$s%nAfter deletion of 3 't' symbols: %2$s%n", 
				input, splitAndSort(input));
	}
	
	public static String splitAndSort(String inputStr) {
		String[] charArr = inputStr.split("");
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < charArr.length; i++) {
			if (!strBuilder.toString().toLowerCase().
					contains(charArr[i].toLowerCase())) {
				strBuilder.append(charArr[i]);
			}
		}
		//do sort
		return strBuilder.toString();
	}

}
