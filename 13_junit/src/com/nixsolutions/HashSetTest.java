package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.*;
import org.hamcrest.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HashSetTest<E> {
	private HashSet<String> hashSet;
	private HashSet<String> hashSetClone;
	private ArrayList<String> listData;
	private int count = 0;
	private int sizeSet = 0;
	private boolean elementExist = false;

	@Before
	public void NeededDataForTesting() {
		hashSet = new HashSet<String>();
		listData = new ArrayList<>();
		hashSetClone = new HashSet<String>();
		listData.add("First");
		listData.add("Second");
		listData.add("Third");
	}

	@After
	public void tearDown() {
		hashSet.clear();
		listData.clear();
		hashSetClone.clear();
	}

	@Test
	public void shouldBeAddedElementToHashSetIfItIsNotAlreadyPresent() {
		// given
		hashSet.add(listData.get(0));
		// then
		Assert.assertTrue(hashSet.contains(listData.get(0)));
	}

	@Test
	public void shouldBeRemoveElementInHashSetIfItIsPresent() {
		// given
		hashSet.addAll(listData);
		// when
		hashSet.remove(listData.get(2));
		// then
		Assert.assertTrue(hashSet.contains(listData.get(0)));
		Assert.assertTrue(hashSet.contains(listData.get(1)));
		Assert.assertFalse(hashSet.contains(listData.get(2)));
	}

	@Test
	public void shouldBeDetermineQuantityOfElementsInHashSet() {
		// given
		hashSet.addAll(listData);
		// when
		count = listData.size();
		sizeSet = hashSet.size();
		// then
		Assert.assertEquals(sizeSet, count);

	}

	@Test
	public void shouldBeElementNotAddedToHashSetBecauseItIsAlreadyPresentInHashSet() {
		// given
		hashSet.add(listData.get(0));
		// when
		hashSet.add(listData.get(0));
		// then
		Assert.assertTrue(hashSet.size() == 1);
		Assert.assertFalse(hashSet.size() == 2);
	}

	@Test
	public void shouldBeCloneHashSetToNewHashSet() {
		// given
		hashSet.addAll(listData);
		// when
		hashSetClone = (HashSet<String>) hashSet.clone();
		// then
		Assert.assertEquals(hashSetClone, hashSet);
	}

	@Test
	public void shouldBeContainSpecifiedElementInHashSet() {
		// given
		hashSet.add(listData.get(0));
		// when
		elementExist = hashSet.contains(listData.get(0));
		// then
		Assert.assertTrue(elementExist);
	}

	@Test
	public void shouldBeClearHashSet() {
		// given
		hashSet.addAll(listData);
		// when
		hashSet.clear();
		// then
		Assert.assertTrue(hashSet.size() == 0);
	}

}
