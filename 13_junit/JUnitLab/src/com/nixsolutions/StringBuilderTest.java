package com.nixsolutions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringBuilderTest {

	private StringBuilder strBuilderObjToTest;

	@Before
	public void setUpBeforeTests() {
		strBuilderObjToTest = new StringBuilder("Initial string");
	}

	@Test
	public void shouldAppendValueToTheEndOfString() {
		// given
		String srtValueToAppend = "Aloha";
		String expectedValue = "Initial stringAloha";
		// when
		strBuilderObjToTest.append(srtValueToAppend);
		// then
		assertEquals(expectedValue, strBuilderObjToTest.toString());
	}

	@Test
	public void shouldInsertValueInGivenPosition() {
		// given
		String srtValueToInsert = "Aloha";
		String expectedValue = "IniAlohatial string";
		int position = 3;
		// when
		strBuilderObjToTest.insert(position, srtValueToInsert);
		// then
		assertEquals(expectedValue, strBuilderObjToTest.toString());
	}

	@Test
	public void shouldDeleteSubstringBetweenGivenIndexes() {
		// given
		String expectedValue = "Initistring";
		int start = 5;
		int end = 8;
		// when
		strBuilderObjToTest.delete(start, end);
		// then
		assertEquals(expectedValue, strBuilderObjToTest.toString());
	}

	@Test
	public void shouldDeleteCharAtGivenPosition() {
		// given
		String expectedValue = "Initial tring";
		int position = 8;
		// when
		strBuilderObjToTest.deleteCharAt(position);
		// then
		assertEquals(expectedValue, strBuilderObjToTest.toString());
	}

	@Test
	public void shouldReplaceSpecifiedSubstringWithGivenValue() {
		// given
		String srtValueToReplaceWith = "Aloha";
		String expectedValue = "Initial Aloha";
		int start = 8;
		int end = 14;
		// when
		strBuilderObjToTest.replace(start, end, srtValueToReplaceWith);
		// then
		assertEquals(expectedValue, strBuilderObjToTest.toString());
	}

	@Test
	public void shouldReverseString() {
		// given
		String expectedValue = "gnirts laitinI";
		// when
		strBuilderObjToTest.reverse();
		// then
		assertEquals(expectedValue, strBuilderObjToTest.toString());
	}

	@Test
	public void shouldReturnFirstIndexOfSpecifiedSubstring() {
		// given
		String valueToFind = "i";
		int expectedValue = 11;
		// when
		int actualValue = strBuilderObjToTest.lastIndexOf(valueToFind);
		// then
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void shouldReturnLastIndexOfSpecifiedSubstring() {
		// given
		String valueToFind = "string";
		int expectedValue = 8;
		// when
		int actualValue = strBuilderObjToTest.indexOf(valueToFind);
		// then
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void shouldReturnSubstringBetweenGivenIndexes() {
		// given
		String expectedValue = "ial s";
		// when
		String actualValue = strBuilderObjToTest.substring(4, 9);
		// then
		assertEquals(expectedValue, actualValue);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldGetExceptionWhenInsertIndexIsOutOfRange() {
		int position = 15;
		exception.expect(StringIndexOutOfBoundsException.class);
		exception.expectMessage("String index out of range");

		strBuilderObjToTest.insert(position, "what is it");
	}

	@Test
	public void shouldGetExceptionWhenReplaceIndexOrderInvalid() {
		int start = 0;
		int end = -3;
		exception.expect(IndexOutOfBoundsException.class);
		exception.expectMessage("start > end");

		strBuilderObjToTest.replace(start, end, "what is it");
	}

}
