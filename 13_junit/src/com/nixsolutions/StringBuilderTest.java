package com.nixsolutions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by konstantin on 12/3/2015.
 */
public class StringBuilderTest {

    private String testString = "Here is some test text";
    private StringBuilder stringBuilder = new StringBuilder();

    @Before
    public void setUp() {

        stringBuilder = new StringBuilder(testString);
    }

    @After
    public void tearDown() {

        stringBuilder = new StringBuilder();
    }

    @Test
    public void appendObjectShouldAppendStringRepresentationOfObject() {

        Object object = new Object();
        stringBuilder.append(object);
        assertEquals(stringBuilder.toString(), testString + object.toString());
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

            reverseString += testString.toCharArray()[i];
        }
        assertEquals(stringBuilder.toString(), reverseString);
    }

    @Test
    public void toStringShouldRepresentCharsSequenceAsString() {

        assertEquals(String.valueOf(stringBuilder), testString);
    }
}
