/**
 * 
 */
package com.nixsolutions;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ������
 *
 */
public class Anagram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String firstWord;
		String secondWord;
		// Enter words
		System.out.print("Please enter some word:\n");
		Scanner in = new Scanner(System.in);
		firstWord = in.nextLine();
		System.out.print("Please enter another word:\n");
		secondWord = in.nextLine();
		in.close();
		if (isAnagram(firstWord, secondWord)) {
			System.out.print("first and second word are anagram:\n");

		} else {
			System.out.print("first and second word are not anagram:\n");

		}

	}

	private static boolean isAnagram(String firstStr, String secondStr) {
		// Convert to char massive
		char[] first = firstStr.toLowerCase().replace(" ", "").replaceAll("[\\.,!?-]", "").toCharArray();
		char[] second = secondStr.toLowerCase().replace(" ", "").replaceAll("[\\.,!?-]", "").toCharArray();
		Arrays.sort(first);
		Arrays.sort(second);
		// Check if equal
		return Arrays.equals(first, second);
	}

}
