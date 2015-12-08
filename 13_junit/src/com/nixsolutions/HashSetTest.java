package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.*;
import org.junit.Before;
import org.junit.Test;


public class HashSetTest {
	private HashSet<String> hashSetExample;
	private static String FIRST_STRING_DATA = "First";
	private static String SECOND_STRING_DATA = "Second";
	private static String THIRD_STRING_DATA = "Third";

	@Before
	public void NeededDataForTesting() {
		hashSetExample = new HashSet<String>();
	}

	@Test
	public void shouldBeRemoveAllElementsInHashSet() {
		// given
		ArrayList<String> dataCollections = new ArrayList<String>();
		dataCollections.add(FIRST_STRING_DATA);
		dataCollections.add(SECOND_STRING_DATA);
		dataCollections.add(SECOND_STRING_DATA);
		hashSetExample.addAll(dataCollections);
		//when
		hashSetExample.removeAll(dataCollections);
		dataCollections.clear();
		// then
		Assert.assertFalse(hashSetExample.contains(FIRST_STRING_DATA));
		Assert.assertFalse(hashSetExample.contains(SECOND_STRING_DATA));
		Assert.assertFalse(hashSetExample.contains(THIRD_STRING_DATA));

	}

	@Test
	public void shouldBeRemoveElementInHashSetIfItIsPresent() {
		// given
		hashSetExample.add(FIRST_STRING_DATA);
		hashSetExample.add(SECOND_STRING_DATA);
		hashSetExample.add(THIRD_STRING_DATA);
		// when
		hashSetExample.remove(THIRD_STRING_DATA);
		// then
		Assert.assertTrue(hashSetExample.contains(FIRST_STRING_DATA));
		Assert.assertTrue(hashSetExample.contains(SECOND_STRING_DATA));
		Assert.assertFalse(hashSetExample.contains(THIRD_STRING_DATA));
	}

	@Test
	public void shouldBeDetermineQuantityOfElementsInHashSet() {
		// given
		hashSetExample.add(FIRST_STRING_DATA);
		hashSetExample.add(SECOND_STRING_DATA);
		hashSetExample.add(THIRD_STRING_DATA);
		int countOfAddedDataStrings = 3;
		int sizeSet = 0;
		// when
		sizeSet = hashSetExample.size();
		// then
		Assert.assertEquals(sizeSet, countOfAddedDataStrings);

	}

	@Test
	public void shouldNotAddDuplicates() {
		// given
		hashSetExample.add(FIRST_STRING_DATA);
		// when
		hashSetExample.add(FIRST_STRING_DATA);
		// then
		Assert.assertTrue(hashSetExample.size() == 1);
		Assert.assertFalse(hashSetExample.size() == 2);
	}

	@Test
	public void shouldBeCloneHashSetToNewHashSet() {
		// given
		HashSet<String> hashSetClone = new HashSet<String>();
		hashSetExample.add(FIRST_STRING_DATA);
		hashSetExample.add(SECOND_STRING_DATA);
		hashSetExample.add(THIRD_STRING_DATA);
		// when
		hashSetClone = (HashSet<String>) hashSetExample.clone();
		// then
		Assert.assertEquals(hashSetClone, hashSetExample);
	}

	@Test
	public void shouldBeCheckThatElementContainsInHashSet() {
		// given
		boolean elementExist = false;
		hashSetExample.add(FIRST_STRING_DATA);
		// when
		elementExist = hashSetExample.contains(FIRST_STRING_DATA);
		// then
		Assert.assertTrue(elementExist);
	}

	@Test
	public void shouldBeClearHashSet() {
		// given
		hashSetExample.add(FIRST_STRING_DATA);
		hashSetExample.add(SECOND_STRING_DATA);
		hashSetExample.add(THIRD_STRING_DATA);
		// when
		hashSetExample.clear();
		// then
		Assert.assertTrue(hashSetExample.size() == 0);
	}

}