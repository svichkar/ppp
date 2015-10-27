package com.nixsolutionsTests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class newTest {

	HashSet<String> hs;

	@Before
	public void initializeVariableHashSet() {
		// Given block for all tests in this class
		hs = new HashSet<>();

	}

	@Test
	public void elementShouldBeAddedToHashSet() {

		hs.add("FirstElement"); // When section

		assertTrue(hs.contains("FirstElement")); // Than section

	}

	@Test
	public void elementShouldBeDeletedFromHashSet() {

		hs.add("ObjectToDelete");
		assertTrue(hs.contains("ObjectToDelete"));
		hs.remove("ObjectToDelete");
		assertTrue(!hs.contains("ObjectToDelete"));

	}

	@Test
	public void hashSetShouldBeEmptyAfterClearCommand() {

		hs.add("1");
		hs.add("2");
		hs.add("3");
		hs.add("4");
		hs.add("5");
		hs.clear();
		assertTrue(hs.isEmpty());

	}

	@Test
	public void hashSetShouldContainOnlyUniqueValues() {

		hs.add("1");
		hs.add("2");
		hs.add("3");
		hs.add("4");
		hs.add("5");
		hs.add("1");
		hs.add("2");
		hs.add("3");
		hs.add("4");
		hs.add("5");
		assertTrue(hs.size() == 5);
		assertTrue(hs.contains("1"));
		assertTrue(hs.contains("2"));
		assertTrue(hs.contains("3"));
		assertTrue(hs.contains("4"));
		assertTrue(hs.contains("5"));

	}

	@Test
	public void hashSetShouldAddNullValueCorectly() {

		hs.add(null);
		assertTrue(hs.contains(null));

	}

	@Test
	public void hashSetShouldBeCorrectlyClonnedByCloneCommand() {

		hs.add("1");
		hs.add("2");
		hs.add("3");
		hs.add("4");
		hs.add("5");

		HashSet<String> hs2 = new HashSet<>();
		hs2 = (HashSet<String>) hs.clone();
		assertEquals(hs, hs2);

	}

	@Test
	public void hashSetMethodSizeShouldReturnCorrectValue() {

		hs.add("1");
		hs.add("2");
		hs.add("3");
		hs.add("4");
		hs.add("5");
		assertEquals(hs.size(), 5);

	}

}
