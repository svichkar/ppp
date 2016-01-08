package com.nixsolutions.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StrBuilderTest {

	private StringBuilder builder; 
	private String testSrringA;
	private String testStringB;

	@Before
	public void initiationOfTheData() {
		builder = new StringBuilder();
		testSrringA = "test";
		testStringB = "testirovanie";
		builder.append(testSrringA);
	}

	@Test
	public void shouldCreateBuilderWithSpecifiedString() {
		builder = new StringBuilder(testSrringA);
		assertEquals(testSrringA, builder.toString());
	}

	@Test
	public void shouldAppendGivenArgumentWithString() {
		builder.append(testStringB);
		assertEquals(testSrringA + testStringB, builder.toString());
	}

	@Test
	public void shouldSubstringFromSpecifiedPosition() {
		builder.append(testStringB);
		assertEquals(testStringB, builder.substring(4));
	}

	@Test
	public void shouldAppendMultipleTypes() {
		assertEquals("testa5falsestring0.3", builder.append('a').append(5)
				.append(false).append("string").append(0.3d).toString());
	}

	@Test
	public void shouldInsertStringAtGivenPosition() {
		builder.insert(0, testStringB);
		assertEquals(testStringB + testSrringA, builder.toString());
	}

	@Test
	public void shouldReplaceStringFromSpecificBeginIndexAndEndIndex() {
		builder.replace(0, 4, testStringB);
		assertEquals(testStringB, builder.toString());
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
