package com.nixsolutions;

import java.io.ObjectInputStream.GetField;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Class for validating anagrams
 * 
 * @author maxb
 *
 */
public class Anagram {

	public static void main(String[] args) {

		String test1 = "testA, testb";
		String test2 = "Atest# Btest";

		// /Getting words from original statement
		String[] rez1 = getWordsFromStatement(test1);
		String[] rez2 = getWordsFromStatement(test2);

		if (checkThatStringsAreAnagrams(joinStrings("", rez1),
				joinStrings("", rez2))) {
			System.out.printf("String \"%1s\" and \"%2s\" are anagrams%n",
					test1, test2);
		} else {
			System.out.printf("String \"%1s\" and \"%2s\" are not anagrams%n",
					test1, test2);
		}

	}

	/**Method for getting words from statement. Ignore special characters 
	 * 
	 * @param strForParsing User-defined string
	 * @return Returns array of string
	 */
	private static String[] getWordsFromStatement(String strForParsing) {
		String patternForNonWords = "[. ,#;;]+";
		return strForParsing.split(patternForNonWords);
	}

	/**Method for validating that two string are anagrams 
	 * 
	 * @param s1 First string after parsing 
	 * @param s2 Second string after parsing
	 * @return
	 */
	private static boolean checkThatStringsAreAnagrams(String s1, String s2) {
		boolean result = false;
		if (s1.length() == s2.length()) {
			char[] chars1 = s1.toLowerCase().toCharArray();
			char[] chars2 = s2.toLowerCase().toCharArray();

			Arrays.sort(chars1);
			Arrays.sort(chars2);

			s1 = String.valueOf(chars1);
			s2 = String.valueOf(chars2);
			if (s1.compareTo(s2) == 0) {
				result = true;
			}
		}
		return result;
	}

	/**Method for joing all string of array into one string
	 * 
	 * @param delimiter String that can be used for joining
	 * @param strArray Array string after parsing
	 * @return
	 */
	private static String joinStrings(String delimiter, String[] strArray) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strArray.length; i++) {
			if (i != strArray.length - 1) {
				builder.append(strArray[i]);
				builder.append(delimiter);
			} else {
				builder.append(strArray[i]);
			}
		}
		return builder.toString();
	}

}
