package com.nixsolutions;

import org.junit.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * Created by kozlovskij on 12/2/2015.
 */


public class StringBuilderTest {
    private StringBuilder builder;
    private static String INPUTSTRING = "abc";
    private String testString = "someText";
    private char[] chars = {'d', 'e', 'f'};

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        builder =  new StringBuilder();
        builder.append(INPUTSTRING);
    }

    @org.junit.Test
    public void shouldAppendStringToTail() {
        String temp = INPUTSTRING + testString;
        builder.append(testString);
        Assert.assertEquals(temp, builder.toString());
    }

    @org.junit.Test
    public void shouldAppendCharSequenceToTail() {
        String expectedString = "abcdef";
        builder.append(chars);
        Assert.assertEquals(expectedString,builder.toString());
    }

    @org.junit.Test
    public void shouldInsertSpecifiedStringAtSpecifiedPosition() {
        String expectedString = "asomeTextbc";
        builder.insert(1, testString);
        Assert.assertEquals(expectedString, builder.toString());
    }

    @org.junit.Test
    public void shouldReturnExceptionWhenInsertInPositionGreaterThanLength() throws IndexOutOfBoundsException {
        exception.expect(IndexOutOfBoundsException.class);
        builder.insert(100, testString);
    }

    @org.junit.Test
    public void shouldReplaceStringInRange() {
        String expectedString = "someTextbc";
        builder.replace(0, 1, testString);
        Assert.assertEquals(expectedString, builder.toString());
    }

    @org.junit.Test
    public void shouldDeleteSpecifiedRange() {
        String expectedString = "bc";
        builder.delete(0, 1);
        Assert.assertEquals(expectedString, builder.toString());
    }

    @org.junit.Test
    public void shouldReverseString() {
        String expectedString = "cba";
        builder.reverse();
        Assert.assertEquals(expectedString, builder.toString());
    }

    @org.junit.Test
    public void shouldReturnSubstringFromPositionToEnd() {
        String expectedString = "bc";
        Assert.assertEquals(expectedString, builder.substring(1));
    }

    @Test
    public void shouldReturnExceptionWhenIndexForSubstringIsNegative() throws IndexOutOfBoundsException {
        exception.expect(IndexOutOfBoundsException.class);
        builder.substring(-1);
    }
}
