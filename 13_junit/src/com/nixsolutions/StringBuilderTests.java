package com.nixsolutions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sobolenko on 2/22/2016.
 */
public class StringBuilderTests {

    StringBuilder stringBuilderForTesting;
    String inputString = "string of the last occurrence of the specified substring";

    @Before
    public void initializeEnv() {
        stringBuilderForTesting = new StringBuilder(inputString);
    }

    @Test
    public void stringBuilderAppendTest() {
        assertTrue(stringBuilderForTesting.append(" first").toString().contains("first"));
    }

    @Test
    public void stringBuilderIndexOfTest() {
        assertTrue(stringBuilderForTesting.indexOf("i") == 3);
    }

    @Test
    public void stringBuilderDeleteChatAtTest() {
        assertFalse(stringBuilderForTesting.deleteCharAt(14).toString().contains("l"));
    }

    @Test
    public void stringBuilderReplaceTest() {
        assertTrue(stringBuilderForTesting.replace(4, 10, "insert").toString().contains("insert") && stringBuilderForTesting.charAt(6) == 's');
    }

    @Test
    public void stringBuilderSubstringTest() {
        assertTrue(stringBuilderForTesting.substring(4, 6).equals("ng"));
    }

    @Test
    public void stringBuilderReverseTest() {
        assertTrue(stringBuilderForTesting.reverse().toString().equals("gnirtsbus deificeps eht fo ecnerrucco tsal eht fo gnirts"));
    }

    @Test
    public void stringBuilderLastIndexOfTest() {
        assertTrue(stringBuilderForTesting.lastIndexOf("string") == 50);
    }

    @Test
    public void stringBuilderCodePointAtOfTest() {
        assertTrue(stringBuilderForTesting.codePointAt(14) == Character.valueOf('l'));
    }

    @After
    public void clearAll() {
        stringBuilderForTesting = new StringBuilder(inputString);
    }
}
