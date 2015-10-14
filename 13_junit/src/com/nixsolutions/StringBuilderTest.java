package com.nixsolutions;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

public class StringBuilderTest {
    StringBuilder initString;

    @Before
    public void setUpInitString() {
	initString = new StringBuilder("InitialString");
    }

    @Test
    public void shouldAppendStringAtEndOfIbitialString() {
	initString.append("End");
	Assert.assertTrue(initString.toString().endsWith("End"));
    }

    @Test
    public void shouldAppendBooleanAtEndOfIbitialString() {
	initString.append(Boolean.TRUE);
	Assert.assertTrue(initString.toString().endsWith("true"));
    }

    @Test
    public void shouldAppendCharAtEndOfIbitialString() {
	initString.append('A');
	Assert.assertTrue(initString.toString().endsWith("A"));
    }

    @Test
    public void shouldAppendDoubleAtEndOfIbitialString() {
	initString.append(25.0);
	Assert.assertTrue(initString.toString().equals("InitialString25.0"));
    }

    @Test
    public void shouldReturnCharByIndex() {
	char ch = initString.charAt(3);
	Assert.assertTrue('t' == ch);
    }

    @Test
    public void shouldReturnIndexOfFirstOccurrence() {
	int index = initString.indexOf("Str");
	Assert.assertEquals(7, index);
    }

    @Test
    public void shouldInsertStringIntoInitialString() {
	initString.insert(5, "Insert");
	Assert.assertEquals("InitiInsertalString", initString.toString());
    }

    @Test
    public void shouldInsertBooleanIntoInitialString() {
	initString.insert(2, Boolean.FALSE);
	Assert.assertEquals("InfalseitialString", initString.toString());
    }

    @Test
    public void shouldInsertLongIntoInitialString() {
	initString.insert(7, 123456);
	Assert.assertEquals("Initial123456String", initString.toString());
    }

    @Test
    public void shouldReturnSubstringOfInitialString() {
	String subStr = initString.substring(3, 9);
	Assert.assertEquals("tialSt", subStr);
    }
}