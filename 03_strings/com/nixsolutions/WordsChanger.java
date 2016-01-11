package com.nixsolutions;

import java.util.Arrays;

/**
 * 
 * @author kryzhanovskiy
 *
 */
public class WordsChanger {

	/**
	 * The method reverses letters in an entered string
	 * 
	 * @param string
	 *            any string
	 * @return a string with reversed direction of words
	 */
	public String stringReverse(String string) {

		char[] temp = string.toCharArray();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < temp.length; i++) {
			builder.append(temp[temp.length - i - 1]);
		}
		return builder.toString();
	}

	/**
	 * the method deletes chosen number of particular words in a given direction
	 * 
	 * @param userInput
	 *            a string from which method will delete words
	 * @param toDelete
	 *            a word which will be deleted
	 * @param n
	 *            how many instances of chosen word will be deleted
	 * @param direction
	 *            set a start point for deleting direction, can be "true" in
	 *            case of left to right direction (from start of a string) or
	 *            "false" in case of right to left direction (from end of a
	 *            string)
	 * @return a string with deleted words
	 */
	public String symbolsDeletter(String userInput, String toDelete, int n, boolean direction) {
		StringBuilder builder = new StringBuilder(userInput);
		int counter = 1;

		try {
			while (counter <= n) {
				if (direction) {
					builder.deleteCharAt(builder.indexOf(toDelete));
				} else {
					builder.deleteCharAt(builder.lastIndexOf(toDelete));
				}
				counter++;
			}
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			System.out.println("the string does not contain so many symbols of \"" + toDelete
					+ "\" or the letter does not exist in the string");
		}
		return builder.toString();
	}

	/**
	 * the method takes in a string, removes all the duplicates and sort words
	 * in alphabetical order
	 * 
	 * @param userInput
	 *            any string
	 * @return a string with removed duplicates and sorted in alphabetical order
	 */
	public String wordModifier(String userInput) {
		StringBuilder temp = new StringBuilder();
		StringBuilder result = new StringBuilder();
		char[] str = userInput.toLowerCase().toCharArray();

		Arrays.sort(str);
		temp.append(str);

		for (int i = 0; i < temp.length(); i++) {
			if (result.indexOf(temp.substring(i, i + 1)) == -1) {
				result.append(temp.substring(i, i + 1));
			}
		}
		return result.toString().trim();
	}
}
