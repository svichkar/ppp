package com.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

public class Tests {

	public HashSet hsTest;
	public HashSet hsEmpty;

	public int value1 = 10;
	public int value2 = 20;
	public int value3 = 30;
	public int value4 = 40;

	@Before
	public void prepare() {
		hsTest = new HashSet();
		hsEmpty = new HashSet();
		hsTest.add(value1);
		hsTest.add(value2);
		hsTest.add(value3);
	}

	@Test
	public void specifiedUniqueElementShouldBeAddAndReturnTrue() {
		assertTrue(hsTest.add(value4));
		assertTrue(hsTest.containsAll(new HashSet(Arrays.asList(value1, value2, value3, value4))));
		assertEquals(hsTest.size(), 4);
	}

	@Test
	public void specifiedNotUniqueElementShouldntBeAddAndReturnFalse() {
		assertFalse(hsTest.add(value3));
		assertTrue(hsTest.containsAll(new HashSet(Arrays.asList(value1, value2, value3))));
		assertEquals(hsTest.size(), 3);
	}

	@Test
	public void specifiedPresentElementShouldBeRemoveAndReturnTrue() {
		assertTrue(hsTest.remove(value3));
		assertTrue(hsTest.containsAll(new HashSet(Arrays.asList(value1, value2))));
		assertEquals(hsTest.size(), 2);
	}

	@Test
	public void shouldReturnFalseForRemovingIfSpecifiedElementIsNotPresent() {
		assertFalse(hsTest.remove(value4));
		assertTrue(hsTest.containsAll(new HashSet(Arrays.asList(value1, value2, value3))));
		assertEquals(hsTest.size(), 3);
	}

	@Test
	public void shouldReturnsNumbeOfElements() {
		assertEquals(hsTest.size(), 3);
	}

	@Test
	public void shouldReturnTrueIfSetContainsNoElements() {
		assertEquals(hsEmpty.size(), 0);
	}

	@Test
	public void checkIfSpecifiedElementContainsInSet() {
		assertTrue(hsTest.contains(value1));
	}

	@Test
	public void checkIfSpecifiedElementNotContainsInSet() {

		assertFalse(hsTest.contains(value4));
	}

	@Test
	public void shouldReturnsCopyOfHashSet() {
		HashSet hsEmpty = new HashSet();
		hsEmpty = (HashSet) hsTest.clone();
		assertTrue(hsEmpty.containsAll(new HashSet(Arrays.asList(value1, value2, value3))));
		assertEquals(hsEmpty.size(), 3);
	}
}
