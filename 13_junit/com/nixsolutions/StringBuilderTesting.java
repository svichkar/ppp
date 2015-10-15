package com.nixsolutions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringBuilderTesting {
	StringBuilder sb;

	@Before
	public void initialize() {
		sb = new StringBuilder("testingString");
	}

	@Test
	public void shouldAppendStringsAndCharacters() {
		// given
		String inputOne = "test";
		char character = 'C';
		// when
		String output = sb.append(inputOne).append(character).toString();
		// then
		Assert.assertEquals("testingString" + inputOne + character, output);
	}

	@Test
	public void shouldGetSubstring() {
		// given
		int startingPosition = 0;
		int endPosition = 6;
		// when
		String output = sb.substring(startingPosition, endPosition);
		// then
		Assert.assertEquals("testin", output);
	}

	@Test
	public void shouldGetWholeStringIfSubstringIsTakenFromBeginningToEnd() {
		// given
		int startingPosition = 0;
		// when
		String output = sb.substring(startingPosition);
		// then
		Assert.assertEquals("testingString", output);
	}
	
	@Test
	public void shouldReturnLengthOfString() {
		//given
		//when
		int output = sb.length();
		//then
		Assert.assertEquals("testingString".length(), output);
	}
	
	@Test
	public void shouldDeleteStringFromString() {
		//given
		int startingPosition = 0;
		int endPosition = 6;
		//when
		String output = sb.delete(startingPosition, endPosition).toString();
		//then
		Assert.assertEquals("gString", output);
	}
	
	@Test
	public void shouldReverseString() {
		//given
		//when
		String output = sb.reverse().toString();
		//then
		Assert.assertEquals("gnirtSgnitset", output);
	}
	
	@Test
	public void shouldReturnFirstIndexOfString() {
		//given
		String searchString = "g";
		//when
		int output = sb.indexOf(searchString);
		//then
		Assert.assertEquals(6, output);
	}
	
	@Test
	public void shouldInsertStringAtCertainPosition() {
		//given
		String stringToInsert = "tTt";
		int offset = 3;
		//when
		String output = sb.insert(offset, stringToInsert).toString();
		//then
		Assert.assertEquals("testTttingString", output);
	}
	
	@Test
	public void shouldChangeCharAt() {
		//given
		char charToInsert = 'C';
		int position = 3;
		//when
		sb.setCharAt(position, charToInsert);
		//then
		Assert.assertEquals("tesCingString", sb.toString());
	}
	
	@Test
	public void shouldCreateStringBuilderFromStringConcatenation() {
		//given
		String inp1 = "TEST";
		String inp2 = "STR";
		//when
		sb = new StringBuilder(inp1 + inp2);
		//then
		Assert.assertEquals("TESTSTR", sb.toString());
	}
}
