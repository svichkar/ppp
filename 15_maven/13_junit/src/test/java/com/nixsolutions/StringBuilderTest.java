package com.nixsolutions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringBuilderTest {
	private StringBuilder str;

	@Before
	public void setUp() throws Exception {
		str = new StringBuilder("Test String");
	}

	@After
	public void tearDown() {
		str = null;
	}

	@Test
	public void shouldAppendCharAndStringToString() {
		Assert.assertEquals("Test Stringt new", str.append('t').append(" new").toString());
	}

	@Test
	public void shouldInsertCharAndStringToStringAtSpecifiedPosition() {
		Assert.assertEquals("TesaInst String", str.insert(3, 'a').insert(4, "Ins").toString());
	}

	@Test
	public void shouldReturnTheSameResultsForAppendAndInsertToTheEnd() {
		StringBuilder s = new StringBuilder(str);
		Assert.assertEquals(s.append(" text").toString(), str.insert(str.length(), " text").toString());
	}

	@Test
	public void shouldDeleteCharAndSubstringInStringAtSpecifiedPosition() {
		Assert.assertEquals("t String", str.delete(1, 3).deleteCharAt(0).toString());
	}

	@Test
	public void shouldSetAndGetStringLength() {
		str.setLength(20);
		Assert.assertEquals(20, str.length());
	}

	@Test
	public void shouldReplaceSubstringWithSpecifiedString() {
		Assert.assertEquals("TestNewing", str.replace(4, 8, "New").toString());
	}

	@Test
	public void shouldReverseString() {
		Assert.assertEquals("gnirtS tseT", str.reverse().toString());
	}

	@Test
	public void shouldGetSubstring() {
		Assert.assertEquals("t St", str.substring(3, 7).toString());
	}

	@Test
	public void shouldGetOriginalStringForSubstringFromBeginningToEnd() {
		Assert.assertEquals(str.toString(), str.substring(0, str.length()).toString());
	}

}
