package com.nixsolutions.second;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by konstantin on 12/3/2015.
 */
public class StringBuilderTest {

    private String testString = "Here is some test text";
    private StringBuilder stringBuilder;

    @Before
    public void setUp() {

        stringBuilder = new StringBuilder(testString);
    }

    @Test
    public void appendStringShouldAppendSpecifiedString() {

        String appString = new String("this is text to append");
        stringBuilder.append(appString);
        assertEquals(stringBuilder.toString(), testString + appString);
    }

    @Test
    public void appendCharArrayShouldAppendStringRepresentationArray() {

        char[] array = {'a', 'b', 'c', 'd'};
        stringBuilder.append(array);
        assertEquals(stringBuilder.toString(), testString + String.valueOf(array));
    }

    @Test
    public void deleteShouldDeleteCharsInSpecifiedSubstring() throws IndexOutOfBoundsException {

        stringBuilder.delete(0, 5);
        assertEquals(stringBuilder.toString(), testString.substring(5));
    }

    @Test
    public void deleteCharAtShouldDeleteCharAtSpecifiedPosition() throws IndexOutOfBoundsException {

        int index = 5;

        String result = new String();
        for (int i = 0; i < testString.toCharArray().length; i++) {
            if (i != index) {
                result += testString.charAt(i);
            }
        }
        stringBuilder.deleteCharAt(index);

        assertEquals(stringBuilder.toString(), result);
    }

    @Test
    public void replaceShouldReplaceCharsInSpecifiedSubstring() throws IndexOutOfBoundsException {

        String s = "something new";
        stringBuilder.replace(0, 5, s);
        assertEquals(stringBuilder.toString(), s + testString.substring(5));
    }

    @Test
    public void insertStringShouldInsertStringIntoCharSequence() throws IndexOutOfBoundsException {

        String s = "insert some words";
        stringBuilder.insert(0, s);
        assertEquals(stringBuilder.toString(), s + testString);
    }

    @Test
    public void lengthShouldReturnInputStringLenght() {

        assertEquals(stringBuilder.length(), testString.length());
    }

    @Test
    public void substringShouldReturnNewStringOfCharsSequence() throws IndexOutOfBoundsException {

        String result = stringBuilder.substring(1, 5);
        assertEquals(result, testString.substring(1, 5));
    }

    @Test
    public void reverseShouldRotateCharsInSequence() {

        stringBuilder.reverse();

        String reverseString = "";
        for (int i = testString.toCharArray().length - 1; i >= 0; i--) {

            reverseString += testString.charAt(i);
        }
        assertEquals(stringBuilder.toString(), reverseString);
    }

    @Test
    public void toStringShouldRepresentCharsSequenceAsString() {

        assertEquals(String.valueOf(stringBuilder), testString);
    }
}
