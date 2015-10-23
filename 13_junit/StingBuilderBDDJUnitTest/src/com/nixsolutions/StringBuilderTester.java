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
		Assert.assertEquals(testObject.toString(), "a");
	}

	@Test
	public void isStringAppended() {
		// When
		testObject.append("New test string");
		// Then
		Assert.assertTrue(testObject.toString().endsWith("New test string"));
	}

	@Test
	public void isLengthReturneIntegerLength() {
		// Then
		Assert.assertThat(testObject.length(), IsInstanceOf.instanceOf(Integer.class));
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
		testObject.append('a');

		Assert.assertTrue(testObject.charAt(0) == 'a');
	}
	
	@Test
	public void isStringDeleted(){
		testObject.append("New test string");
		Assert.assertTrue(testObject.toString().equals("New test string"));
		testObject.delete(3, 8);
		Assert.assertTrue(testObject.toString().equals("New string"));

	}
}
