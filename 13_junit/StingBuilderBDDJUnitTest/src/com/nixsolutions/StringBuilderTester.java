package com.nixsolutions;

import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.StringEndsWith;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringBuilderTester {

	private StringBuilder testObject;

	// Given
	@Before
	public void createStringBuilderObject() {
		testObject = new StringBuilder();
	}

	@Test
	public void isCharacterApPended() {
		// When
		testObject.append('a');
		// Then
		Assert.assertEquals("a",testObject.toString());
	}

	@Test
	public void isStringAppended() {
		// When
		testObject.append("New test string");
		// Then
		Assert.assertTrue(testObject.toString().endsWith("New test string"));
	}

	@Test
	public void isMultipleStringConcatenated() {
		// Then
		Assert.assertEquals("New test string", testObject.append("New ").append("test ").append("string").toString());
	}

	@Test
	public void isNumberAndStringConcatenated() {
		// Then
		Assert.assertEquals("String 1 2.24 3.14", testObject.append("String ").append(1).append(" ").append(2.24f).append(" ").append(3.14d).toString());
	}

	@Test
	public void isSetLengthReturnStringBuilderObjectWithExpectedLength() {
		// When
		testObject.setLength(5);
		// Then
		Assert.assertTrue(testObject.length() == 5);
	}

	@Test
	public void isReturnedCharByIndex() {
		// when
		testObject.append('a');
		// then
		Assert.assertTrue(testObject.charAt(0) == 'a');
	}

	@Test
	public void isStringDeleted() {
		// when
		testObject.append("New test string");
		testObject.delete(3, 8);
		// then
		Assert.assertTrue(testObject.toString().equals("New string"));

	}
}
