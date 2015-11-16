package com.nixsolutions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Strings {

	public static void main(String[] args) {

		System.out.println("===================\nPlease enter a string");
		Scanner inStr = new Scanner(System.in);
		String userStr = inStr.nextLine();
		String strResult = myReverse(userStr);
		System.out.println("Reverse string: " + strResult);
		System.out.println("===================\nPlease enter a string");
		inStr = new Scanner(System.in);
		StringBuilder newStr = new StringBuilder(inStr.nextLine());
		System.out.println("Please enter a character");
		inStr = new Scanner(System.in);
		userStr = inStr.nextLine();
		System.out.println("Please enter a number of deletions");
		inStr = new Scanner(System.in);
		int n = Integer.valueOf(inStr.nextLine());
		System.out.println("Please choose the direction: left or right");
		inStr = new Scanner(System.in);
		String direction = inStr.nextLine();
		System.out.println("Result: " + removeCharacter(newStr, userStr, n, direction));
		System.out.println("===================\nPlease enter a string");
		inStr = new Scanner(System.in);
		userStr = inStr.nextLine();
		changeString(userStr);
		inStr.close();

	}

	private static String myReverse(String str) {
		char[] strArray = str.toCharArray();
		char[] strArrayReverse = new char[str.length()];
		for (int i = 0; i < strArray.length; i++) {
			strArrayReverse[i] = strArray[str.length() - i - 1];
		}
		return new String(strArrayReverse);

	}

	private static StringBuilder removeCharacter(StringBuilder str, String ch, int n, String dir) {
		int j = 0;
		if (dir.toLowerCase().equals("left")) {
			for (int i = 0; i < str.length(); i++) {
				if ((j < n) && (Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(ch.charAt(0)))) {
					str = str.deleteCharAt(i);
					j++;
				}
			}
		} else if (dir.toLowerCase().equals("right")) {
			for (int i = str.length() - 1; i >= 0; i--) {
				if ((j < n) && (Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(ch.charAt(0)))) {
					str = str.deleteCharAt(i);
					j++;
				}
			}
		} else
			str = new StringBuilder("You entered an incorrect direction");
		return str;

	}

	private static void changeString(String str) {
		StringBuilder result = new StringBuilder();
		char[] strArray = str.toCharArray();
		java.util.Arrays.sort(strArray);
		for (int i = 0; i < strArray.length; i++) {
			if (result.toString().toLowerCase().contains(String.valueOf(Character.toLowerCase(strArray[i]))) == false)
				result.append(strArray[i]);
		}
		System.out.println("Result: " + result.toString());
	}

}
