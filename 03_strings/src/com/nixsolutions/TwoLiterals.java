package com.nixsolutions;

public class TwoLiterals {

	public static String firstWord = "Java";
	public static String secondWord = "forever";
	public static String concatenationResult = "";

	public static String concatenationPlusVariant() {
		return concatenationResult = firstWord + " " + secondWord;
	}

	public static String concatenationMethodVariant() {
		return concatenationResult = firstWord.concat(" ").concat(secondWord);
	}

	public static String concatenationSecondMethodVariant() {
		StringBuilder sb = new StringBuilder(firstWord);
		sb.insert(4, " " + secondWord);
		return sb.toString();
	}

	public static String concatenationThirdMethodVariant() {
		StringBuilder sb = new StringBuilder();
		sb.append(firstWord).append(" ").append(secondWord);
		return sb.toString();
	}

	public static void main(String[] args) {
		String resultFirstMerge = concatenationPlusVariant();
		String resultSecondMerge = concatenationMethodVariant();
		String resultThirdMerge = concatenationSecondMethodVariant();
		String resultFourthMerge = concatenationThirdMethodVariant();
		System.out.println(resultFirstMerge);
		System.out.println(resultSecondMerge);
		System.out.println(resultThirdMerge);
		System.out.println(resultFourthMerge);

	}

}
