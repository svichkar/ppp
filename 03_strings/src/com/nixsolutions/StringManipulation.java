package com.nixsolutions;

/**
 * Created by pantiukhin on 1/28/2016. Subject: "Working with Strings"
 */
public class StringManipulation {
    public static void main(String[] args) {
        StringManipulation manipulation = new StringManipulation();
        manipulation.reverseCharactersInWord("Manipulation");
        manipulation.removeFirstOrLastNCharacters('A', 3, "An apple a day keeps the doctor away", true);
        manipulation.removeDuplicatesInWord("Device");
    }

    public String reverseCharactersInWord(String word) {
        char tempStorage;
        char[] arrayOfChars = word.toCharArray();
        int index = arrayOfChars.length - 1;
        for (int i = 0; i < index; i++) {
            tempStorage = arrayOfChars[i];
            arrayOfChars[i] = arrayOfChars[index];
            arrayOfChars[index] = tempStorage;
            index--;
        }
        System.out.println("The reversed word: " + String.copyValueOf(arrayOfChars));
        System.out.println("===============");
        return String.copyValueOf(arrayOfChars);
    }

    public void removeFirstOrLastNCharacters(char charToRemove, int numberOfCharsToRemove, String initialString, boolean removeFromStart) {
        System.out.println("The initial string: \"" + initialString + "\"");
        String stringForManipulation = initialString.toLowerCase();
        char charForManipulation = Character.toLowerCase(charToRemove);
        if (removeFromStart) {
            for (int i = 0; i < numberOfCharsToRemove; i++) {
                stringForManipulation = stringForManipulation.replaceFirst("[" + charForManipulation + "]", "");
            }
        } else {
            stringForManipulation = reverseCharactersInWord(stringForManipulation);
            for (int i = 0; i < numberOfCharsToRemove; i++) {
                stringForManipulation = stringForManipulation.replaceFirst("[" + charForManipulation + "]", "");
            }
            stringForManipulation = reverseCharactersInWord(stringForManipulation);
        }
        System.out.println("The string in which the first " + numberOfCharsToRemove +
                " character(s)'" + charToRemove + "' have been removed: \"" +
                stringForManipulation + "\"");
        System.out.println("===============");
    }

    public void removeDuplicatesInWord(String word) {
        String wordForManipulation = word.toLowerCase();
        System.out.println("The initial word is: " + wordForManipulation);
        char[] arrayOfChars = wordForManipulation.toCharArray();
        for (int i = 0; i < arrayOfChars.length; i++) {
            for (int j = i + 1; j < arrayOfChars.length; j++)
                if (arrayOfChars[i] == arrayOfChars[j]) {
                    arrayOfChars[i] = ' ';
                }
        }
        sort(arrayOfChars);
        System.out.print("The sorted word without the duplicates is : " + String.copyValueOf(arrayOfChars).trim());
    }

    public static void sort(char[] arrayToBeSorted) {
        char b;
        for (int i = 0; i < arrayToBeSorted.length - 1; i++) {
            for (int j = 0; j < arrayToBeSorted.length - i - 1; j++) {
                if (arrayToBeSorted[j] > arrayToBeSorted[j + 1]) {
                    b = arrayToBeSorted[j];
                    arrayToBeSorted[j] = arrayToBeSorted[j + 1];
                    arrayToBeSorted[j + 1] = b;
                }
            }
        }
    }
}
