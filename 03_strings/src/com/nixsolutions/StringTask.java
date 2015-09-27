package com.nixsolutions;

import java.util.Arrays;

public class StringTask {
    public static void main(String[] args) {
	System.out.println(reverseString("Java"));
	System.out.println(removeChars("You know nothing Jon Snow", 'O', 3, false));
	System.out.println(removeDuplicates("aArFgfFfdfdgf"));
    }

    /**
     * Reverts string
     * 
     * @param inputString
     *            is a string for reverting
     * @return reverted string
     */
    public static String reverseString(String inputString) {
	String revertedString = "";
	for (int i = 0; i < inputString.length(); i++) {
	    revertedString = Character.toString(inputString.charAt(i)) + revertedString;
	}
	return revertedString;
    }

    /**
     * Removes specific character from string specified count of times
     * 
     * @param inputString
     *            is string where characters should be removed
     * @param charToRemove
     *            is char to remove from string
     * @param countCharsToRemove
     *            is count of times to remove specific char in input string
     * @param removeFromBeginingOrEnd
     *            is value to define removing from beginning or end of string
     *            (true - from the beginning, false - from the end)
     * @return string where specific character is removed specified count of
     *         times
     */
    public static String removeChars(String inputString, char charToRemove, int countCharsToRemove,
	    boolean removeFromBeginningOrEnd) {
	StringBuilder resultString = new StringBuilder(inputString);
	for (int i = 0; i < countCharsToRemove; i++) {
	    int indexOfCharToRemove = -1;
	    if (removeFromBeginningOrEnd) {
		// get index of char which should be removed (removing from
		// beginning of string)
		indexOfCharToRemove = resultString.toString().toLowerCase()
			.indexOf(Character.toLowerCase(charToRemove));
	    } else {
		// get index of char which should be removed (removing from end
		// of string)
		indexOfCharToRemove = resultString.toString().toLowerCase()
			.lastIndexOf(Character.toLowerCase(charToRemove));
	    }
	    if (indexOfCharToRemove > -1) {
		// remove char in specific index
		resultString = resultString.deleteCharAt(indexOfCharToRemove);
	    } else {
		// break cycle if char is not exist in string
		break;
	    }
	}
	return resultString.toString();
    }

    /**
     * Removes duplicated chars from string and sorts unique chars in
     * alphabetically order
     * 
     * @param inputString
     *            is string where duplicated chars should be removed
     * @return string with unique chars sorted in alphabetically order
     */
    public static String removeDuplicates(String inputString) {
	String resultString = "";
	char[] arrayOfInputChars = inputString.toCharArray();
	int border = 1; // index before which only unique elements
	for (int i = 1; i < arrayOfInputChars.length; i++) {
	    boolean isExist = false;
	    // check if char in index i is exist in unique chars before index
	    // border
	    for (int j = 0; j < border; j++) {
		if (Character.toLowerCase(arrayOfInputChars[i]) == Character.toLowerCase(arrayOfInputChars[j])) {
		    isExist = true;
		}
	    }
	    // if char is not exist in unique chars, swap this one with char in
	    // index border and increase index border
	    if (!isExist) {
		char temp = arrayOfInputChars[border];
		arrayOfInputChars[border] = arrayOfInputChars[i];
		arrayOfInputChars[i] = temp;
		border++;
	    }
	}

	char[] uniqueChars = Arrays.copyOfRange(arrayOfInputChars, 0, border);
	// sort array with unique chars
	for (int i = 0; i < uniqueChars.length - 1; i++) {
	    for (int j = 0; j < uniqueChars.length - 1 - i; j++) {
		if (Character.toLowerCase(uniqueChars[j]) > Character.toLowerCase(uniqueChars[j + 1])) {
		    char temp = uniqueChars[j];
		    uniqueChars[j] = uniqueChars[j + 1];
		    uniqueChars[j + 1] = temp;
		}
	    }
	}
	// build result string
	for (char ch : uniqueChars) {
	    resultString += ch;
	}
	return resultString;
    }
}
