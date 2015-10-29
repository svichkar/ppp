package com.nixsolutions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestsForStringBuilder {

	StringBuilder targetStringBuilder;

	@Before
	public void setStringBuilder() {
		// init
		targetStringBuilder = new StringBuilder();

	}

	@Test
	public void checkThatObjectIsNotNullAfterInit() {
		assertNotNull(targetStringBuilder);
	}

	@Test
	public void checkConcatinatingThreeStrings() {
		//given
		String str1 = "bla1";
		String str2 = "bla2";
		String str3 = "+";
		//when
		targetStringBuilder.append(str1).append(str3).append(str2);
		//then
		assertEquals("bla1+bla2", targetStringBuilder.toString());
	}

	@Test
	public void checkReturningLength() {
		//given
		String str1 = "bla1";
		//when
		targetStringBuilder.append(str1);
		//then
		assertEquals(str1.length(), targetStringBuilder.length());

	}

	@Test(expected = StringIndexOutOfBoundsException.class)
	public void checkThrowingIndexOutOfBoundsEx() {
		Object objNull = null;
		targetStringBuilder.deleteCharAt(10);
	}

	@Test
	public void checkConcatinationStringAndNumber() {
		//given
		String str1 = "bla1";
		Double double1 = 12.234;
		//when
		targetStringBuilder.append(str1).append(double1);
		//then
		assertEquals("bla112.234", targetStringBuilder.toString());
	}

	@Test
	public void checkReturningSubString() {
		//given
		String str1 = "bla1";
		String str2 = "bla2";
		targetStringBuilder.append(str1).append(str2);
		//when
		String result = targetStringBuilder.substring(
				targetStringBuilder.indexOf("1") + 1,
				targetStringBuilder.indexOf("2"));
		//then
		assertEquals("bla", result);
	}

	@Test
	public void checkReplacingByNewString() {
		// given
		String str1 = "bla1";
		String str2 = "bla2";
		targetStringBuilder.append(str1).append(str2);
		// when
		String result = targetStringBuilder.replace(0, 3, "aaa").toString();
		// then
		assertEquals("aaa1bla2", result);
	}

	@Test
	public void checkMakingReverse() {
		// given
		String str1 = "bla1";
		String str2 = "bla2";
		// when
		targetStringBuilder.append(str1).append(str2);
		// then
		assertTrue(targetStringBuilder.reverse().toString()
				.compareTo("2alb1alb") == 0);
	}

	@Test
	public void checkReturningCharAtIndex() {
		// given
		String str1 = "bla1";
		String str2 = "bla2";
		// when
		targetStringBuilder.append(str1).append(str2);
		char result = targetStringBuilder.charAt(0);
		// then
		assertEquals('b', result);
	}

	@Test
	public void ckeckRemovingCharAtIndex() {
		// given
		String str1 = "bla1";
		String str2 = "bla2";
		// when
		targetStringBuilder.append(str1).append(str2);
		targetStringBuilder.deleteCharAt(targetStringBuilder.length() - 1);
		// then
		assertEquals("bla1bla", targetStringBuilder.toString());
	}

	@After
	public void disposeStringBuild() {
		targetStringBuilder = null;
	}

}
