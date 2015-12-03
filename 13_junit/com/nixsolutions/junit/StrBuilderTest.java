package com.nixsolutions.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrBuilderTest {

	StringBuilder builder; 
	String first;
	String second;

	@Before
	public void initiationOfTheData() {
		builder = new StringBuilder();
		first = "test";
		second = "testirovanie";
		builder.append(first);
	}

	@Test
	public void shouldCreateBuilderWithSpecifiedString() {
		builder = new StringBuilder(first);
		assertEquals(first, builder.toString());
	}

	@Test
	public void shouldAppendGivenArgumentWithString() {
		builder.append(second);
		assertEquals(first + second, builder.toString());
	}

	@Test
	public void shouldSubstringFromSpecifiedPosition() {
		builder.append(second);
		assertEquals(second, builder.substring(4));
	}

	@Test
	public void shouldAppendMultipleTypes() {
		assertEquals("testa5falsestring0.3", builder.append('a').append(5)
				.append(false).append("string").append(0.3d).toString());
	}

	@Test
	public void shouldInsertStringAtGivenPosition() {
		builder.insert(0, second);
		assertEquals(second + first, builder.toString());
	}

	@Test
	public void shouldappendChar() {
		builder.append("s");
		assertTrue(builder.toString().endsWith("s"));
	}

	@Test
	public void shouldReplaceStringFromSpecificBeginIndexAndEndIndex() {
		builder.replace(0, 4, second);
		assertEquals(second, builder.toString());
	}

	@Test
	public void shouldDeleteStringFromSpecificBeginIndexAndEndIndex() {
		builder.delete(0, builder.length());
		assertEquals("", builder.toString());
	}

	@Test
	public void shouldDeleteCharFromSpecificIndex() {
		builder.deleteCharAt(0);
		assertTrue(builder.toString().charAt(0) != 't');
	}

	@Test
	public void shouldReverseGivenString() {
		builder.reverse();
		assertEquals("tset", builder.toString());
	}
}
