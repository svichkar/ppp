package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Anagram {

	public static String checkAnagramForTwoSentences(String firstSentence, String secondSentence) {
		String result = "";
		String firstSentenceAfterDeleteSymbols = firstSentence.trim().replace(" ", "");
		String secondSentenceAfterDeleteSymbols = secondSentence.trim().replace(" ", "");
		char[] firstSentenceToArray = new char[firstSentenceAfterDeleteSymbols.length()];
		char[] secondSentenceToArray = new char[secondSentenceAfterDeleteSymbols.length()];
		firstSentenceToArray = firstSentenceAfterDeleteSymbols.toCharArray();
		secondSentenceToArray = secondSentenceAfterDeleteSymbols.toCharArray();
		Arrays.sort(firstSentenceToArray);
		Arrays.sort(secondSentenceToArray);

		if (Arrays.equals(firstSentenceToArray, secondSentenceToArray)) {
			result = "Yes. These sentences are anagram.";

		} else {
			result = "No. These sentences aren't anagram.";
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the first sentence: ");
		String first = reader.readLine();
		System.out.println("Please enter the second sentence: ");
		String second = reader.readLine();
		String result = checkAnagramForTwoSentences(first, second);
		System.out.println(result);

	}

}
