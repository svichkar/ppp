package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashSetTest {
	private HashSet<String> hashSet;
	private ArrayList<String> list = new ArrayList<>();

	@Before
	public void setUp() {
		hashSet = new HashSet<>();		
		list.add("Test Value 1");
		list.add("Test Value 2");
		list.add("Test Value 3");
	}

	@Test
	public void shouldAddElementToHashSet() {
		// given
		hashSet.add(list.get(0));
		hashSet.add(list.get(1));
		// then
		Assert.assertTrue(hashSet.contains(list.get(0)));
		Assert.assertTrue(hashSet.contains(list.get(1)));
	}

	@Test
	public void shouldAddArrayOfElementsToHashSet() {
		// given
		hashSet.addAll(list);
		// then
		Assert.assertTrue(hashSet.containsAll(list));
	}

	@Test
	public void shouldRemoveElementFromHashSet() {
		// given
		hashSet.addAll(list);
		// when
		hashSet.remove(list.get(1));
		// then
		Assert.assertTrue(hashSet.contains(list.get(0)));
		Assert.assertFalse(hashSet.contains(list.get(1)));
		Assert.assertTrue(hashSet.contains(list.get(2)));
	}

	@Test
	public void shouldGetSizeOfHashSet() {
		// given
		hashSet.addAll(list);
		// when
		int size = hashSet.size();
		// then
		Assert.assertTrue(size == 3);
	}

	@Test
	public void shouldCheckOnContains() {
		// given
		hashSet.addAll(list);
		// when
		boolean contains = hashSet.contains(list.get(1));
		boolean notContains = hashSet.contains("Test Value 0");
		// then
		Assert.assertTrue(contains);
		Assert.assertFalse(notContains);
	}

	@Test
	public void shouldCleanHashSet() {
		// given
		hashSet.addAll(list);
		// when
		hashSet.clear();
		// then
		Assert.assertTrue(hashSet.isEmpty());
	}

	@Test
	public void shouldCloneHashSet() {
		// given
		HashSet<String> hashSetCloned;
		hashSet.addAll(list);
		// when
		hashSetCloned = (HashSet<String>) hashSet.clone();
		// then
		Assert.assertEquals(hashSet, hashSetCloned);
	}
	
	@Test
	public void shouldContainsOnlyUniqueElements() {
		// given
		hashSet.addAll(list);		
		// when
		hashSet.add(list.get(1));
		// then
		Assert.assertTrue(hashSet.size() == list.size());
		Assert.assertTrue(hashSet.containsAll(list));
	}
}
