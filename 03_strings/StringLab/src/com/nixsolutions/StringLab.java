package com.nixsolutions;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringLab {

	public static void waysToConcatStr(String firstStr, String secondStr) {
		System.out.println("Using \'+\' operator: " + firstStr + " " + secondStr);
		System.out.println("Using String.concat: " + firstStr.concat(" ").concat(secondStr));
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(firstStr).append(" ").append(secondStr);
		System.out.println("Using StringBuffer: " + strBuffer.toString());
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(firstStr).append(" ").append(secondStr);
		System.out.println("Using StringBuilder: " + strBuilder.toString());
	}

	public static String getInitials(String nameFull) {
		StringBuilder nameBuilder = new StringBuilder();
		String[] nameInitials = nameFull.split(" ");
		for (int i = 0; i < nameInitials.length; i++) {
			nameBuilder.append(nameInitials[i].charAt(0));
		}
		return nameBuilder.toString().toUpperCase();
	}

	public static boolean isAnagram(String firstStr, String secondStr) {
		String firstStrUp = firstStr.toUpperCase();
		String secondStrUp = secondStr.toUpperCase();
		char arrF[] = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS).matcher(firstStrUp).replaceAll("")
				.toCharArray();
		char arrS[] = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS).matcher(secondStrUp).replaceAll("")
				.toCharArray();
		Arrays.sort(arrF);
		Arrays.sort(arrS);
		return Arrays.equals(arrF, arrS);
	}

	public static void main(String[] args) {
		waysToConcatStr("Java", "forever");
		System.out.println("Name initials: " + getInitials("Greenleaf Legolas Thranduilovich"));
		System.out.println(isAnagram("старо, режимность!", "Не. Расторжимость?"));
	}
}
