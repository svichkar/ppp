package com.nixsolutions.junit;

import java.util.HashSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Evgeniy Fomin
 *
 */
public class JunitLab {
	private HashSet<String> mainTestingHashSet;

	@Before
	public void setValuesForTesting() {
		// Given for each of the tests
		mainTestingHashSet = new HashSet<>();
	}

	@Test
	public void checkIfSpecifiedElementsCanBeAdded() {
		int sizeBefore = mainTestingHashSet.size();
		mainTestingHashSet.add("String #1");
		int sizeAfter = mainTestingHashSet.size();
		Assert.assertTrue(sizeBefore < sizeAfter);
		Assert.assertTrue(mainTestingHashSet.contains("String #1"));
	}

	@Test
	public void checkIfSpecifiedElementsCanBeRemoved() {
		mainTestingHashSet.add("String #1");
		mainTestingHashSet.add("String #2");
		int sizeBefore = mainTestingHashSet.size();
		mainTestingHashSet.remove("String #1");
		int sizeAfter = mainTestingHashSet.size();
		Assert.assertTrue(sizeBefore > sizeAfter);
		Assert.assertFalse(mainTestingHashSet.contains("String #1"));
		Assert.assertTrue(mainTestingHashSet.contains("String #2"));
	}

	@Test
	public void checkIfContainsElement() {
		mainTestingHashSet.add("Test string");
		Assert.assertTrue(mainTestingHashSet.contains("Test string"));
	}

	@Test
	public void checkIfClears() {
		mainTestingHashSet.add("Test string");
		mainTestingHashSet.clear();
		Assert.assertFalse(mainTestingHashSet.contains("Test string"));
		Assert.assertTrue(mainTestingHashSet.size() == 0);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void checkIfClones() {
		mainTestingHashSet.add("Value will be cloned");
		HashSet<String> hSetToCompare = (HashSet<String>) mainTestingHashSet.clone();
		Assert.assertEquals(mainTestingHashSet, hSetToCompare);
	}

	@Test
	public void checkDuplicatesCannotBeAdded() {
		mainTestingHashSet.add("String #1");
		mainTestingHashSet.add("String #2");
		Assert.assertFalse(mainTestingHashSet.add("String #1"));
	}

	@Test
	public void checkOtherHashSetCanBeAddedExceptExistingItems() {
		HashSet<String> hSetForAdding = new HashSet<>();

		mainTestingHashSet.add("String #1");
		mainTestingHashSet.add("String #2");

		hSetForAdding.add("String #3");
		hSetForAdding.add("String #4");
		hSetForAdding.add("String #1");
		mainTestingHashSet.addAll(hSetForAdding);
		Assert.assertTrue(mainTestingHashSet.contains("String #3"));
		Assert.assertTrue(mainTestingHashSet.contains("String #4"));
		Assert.assertTrue(mainTestingHashSet.size() == 4);
	}

}
