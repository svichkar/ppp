package com.nixsolutions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringBuilderTest {

	private StringBuilder stringBuilder = null;
	private String dataToStringBuilder = "Testing String Builder";

	@Before
	public void preparingForTests() {
		stringBuilder = new StringBuilder(dataToStringBuilder);
	}

	@After
	public void resetData() {
		stringBuilder = null;
	}

	@Test
	public void shouldBeReturnValidLength() {
		// given
		int actualLength = dataToStringBuilder.length();
		int expectedLenght = 0;
		// when
		expectedLenght = stringBuilder.length();
		// then
		Assert.assertEquals(expectedLenght, actualLength);
	}

	@Test
	public void shouldBeAppendStringToOverallString() {
		// given
		String inputData = " worked";
		String actual = "";
		String expected = "Testing String Builder worked";
		// when
		actual = stringBuilder.append(inputData).toString();
		// then
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void shoulddBeDeleteAllData() {
		// given
		String actual = "";
		String expected = "";
		// when
		actual = stringBuilder.delete(0, stringBuilder.length()).toString();
		// then
		Assert.assertEquals(actual, expected);
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void shoulddBeSetingLength() {
		// given
		int actualLength = stringBuilder.length();
		int expectedLength = 0;
		// when
		stringBuilder.setLength(-100);
		expectedLength = stringBuilder.length();
		// then
		Assert.assertNotEquals(actualLength, expectedLength);
	}
	
	@Test
	public void shouldBeReverseData() {
		// given
		String expected = "redliuB gnirtS gnitseT";		  
		// when
		stringBuilder.reverse();
		String actual = stringBuilder.toString();
		// then
		Assert.assertEquals(expected, actual);
	}
	
	

}
