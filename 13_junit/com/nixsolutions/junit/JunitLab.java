/**
 * 
 */
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
	private HashSet<String> hSet;

	@Before
	public void setValuesForTesting() {
		// Given for each of the tests
		hSet = new HashSet<>();
	}

	@Test
	public void checkIfSpecifiedElementsCanBeAdded() {		
		int sizeBefore = hSet.size();
		hSet.add("String #1");
		int sizeAfter = hSet.size();		
		Assert.assertTrue(sizeBefore < sizeAfter && hSet.contains("String #1"));
	}

	@Test
	public void checkIfSpecifiedElementsCanBeRemoved() {		
		hSet.add("String #1");
		hSet.add("String #2");
		int sizeBefore = hSet.size();
		hSet.remove("String #1");
		int sizeAfter = hSet.size();
		Assert.assertTrue(sizeBefore > sizeAfter && !hSet.contains("String #1") && hSet.contains("String #2"));
	}

	@Test
	public void checkIfContainsElement() {
		hSet.add("Test string");
		Assert.assertTrue(hSet.contains("Test string"));
	}

	@Test
	public void checkIfClears() {
		hSet.add("Test string");
		hSet.clear();
		Assert.assertFalse(hSet.contains("Test string") && hSet.size() > 0);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void checkIfClones() {
		hSet.add("Value will be cloned");
		HashSet<String> hSetToCompare = (HashSet<String>) hSet.clone();
		Assert.assertEquals(hSet, hSetToCompare);
	}

	@Test
	public void checkDuplicatesCannotBeAdded() {
		hSet.add("String #1");
		hSet.add("String #2");
		Assert.assertFalse(hSet.add("String #1"));
	}

	@Test
	public void checkOtherHashSeatCanBeAddedExceptExistingItems() {
		HashSet<String> hSetForAdding = new HashSet<>();

		hSet.add("String #1");
		hSet.add("String #2");

		hSetForAdding.add("String #3");
		hSetForAdding.add("String #4");
		hSetForAdding.add("String #1");
		hSet.addAll(hSetForAdding);
		Assert.assertTrue(hSet.contains("String #3") && hSet.contains("String #4") && hSet.size() == 4);
	}

}
