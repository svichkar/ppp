package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.*;
import org.hamcrest.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HashSetTest {
	private HashSet<String> hashSetExample;
	private HashSet<String> hashSetClone;
	private ArrayList<String> listData;
	private int count = 0;
	private int sizeSet = 0;
	private boolean elementExist = false;

	@Before
	public void NeededDataForTesting() {
		hashSetExample = new HashSet<String>();
		listData = new ArrayList<>();
		hashSetClone = new HashSet<String>();
		listData.add("First");
		listData.add("Second");
		listData.add("Third");
	}

	@After
	public void tearDown() {
		hashSetExample.clear();
		listData.clear();
		hashSetClone.clear();
	}

	@Test
	public void shouldBeAddedElementToHashSetIfItIsNotAlreadyPresent() {
		// given
		hashSetExample.add(listData.get(0));
		// then
		Assert.assertTrue(hashSetExample.contains(listData.get(0)));
	}

	@Test
	public void shouldBeRemoveElementInHashSetIfItIsPresent() {
		// given
		hashSetExample.addAll(listData);
		// when
		hashSetExample.remove(listData.get(2));
		// then
		Assert.assertTrue(hashSetExample.contains(listData.get(0)));
		Assert.assertTrue(hashSetExample.contains(listData.get(1)));
		Assert.assertFalse(hashSetExample.contains(listData.get(2)));
	}

	@Test
	public void shouldBeDetermineQuantityOfElementsInHashSet() {
		// given
		hashSetExample.addAll(listData);
		// when
		count = listData.size();
		sizeSet = hashSetExample.size();
		// then
		Assert.assertEquals(sizeSet, count);

	}

	@Test
	public void shouldBeElementNotAddedToHashSetBecauseItIsAlreadyPresentInHashSet() {
		// given
		hashSetExample.add(listData.get(0));
		// when
		hashSetExample.add(listData.get(0));
		// then
		Assert.assertTrue(hashSetExample.size() == 1);
		Assert.assertFalse(hashSetExample.size() == 2);
	}

	@Test
	public void shouldBeCloneHashSetToNewHashSet() {
		// given
		hashSetExample.addAll(listData);
		// when
		hashSetClone = (HashSet<String>) hashSetExample.clone();
		// then
		Assert.assertEquals(hashSetClone, hashSetExample);
	}

	@Test
	public void shouldBeContainSpecifiedElementInHashSet() {
		// given
		hashSetExample.add(listData.get(0));
		// when
		elementExist = hashSetExample.contains(listData.get(0));
		// then
		Assert.assertTrue(elementExist);
	}

	@Test
	public void shouldBeClearHashSet() {
		// given
		hashSetExample.addAll(listData);
		// when
		hashSetExample.clear();
		// then
		Assert.assertTrue(hashSetExample.size() == 0);
	}

}