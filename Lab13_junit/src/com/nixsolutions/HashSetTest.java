package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashSetTest {
	private HashSet<String> hashSet;

	@Before
	public void setUp() {
		hashSet = new HashSet<>();
	}

	@Test
	public void shouldAddElementToHashSet() {
		// given
		String val1 = "Test Value 1";
		String val2 = "Test Value 2";
		// when
		hashSet.add(val1);
		hashSet.add(val2);
		// then
		Assert.assertArrayEquals(hashSet.toArray(), new String[] { val2, val1 });
	}

	@Test
	public void shouldAddArrayOfElementsToHashSet() {
		// given
		String val1 = "Test Value 1";
		String val2 = "Test Value 2";
		String val3 = "Test Value 3";
		ArrayList<String> list = new ArrayList<>();
		list.add(val2);
		list.add(val3);
		// when
		hashSet.add(val1);
		hashSet.addAll(list);
		// then
		Assert.assertArrayEquals(hashSet.toArray(), new String[] { val2, val1, val3 });
	}

	@Test
	public void shouldRemoveElementFromHashSet() {
		// given
		String val1 = "Test Value 1";
		String val2 = "Test Value 2";
		String val3 = "Test Value 3";
		hashSet.add(val1);
		hashSet.add(val2);
		hashSet.add(val3);
		// when
		hashSet.remove(val2);
		// then
		Assert.assertArrayEquals(hashSet.toArray(), new String[] { val1, val3 });
	}

	@Test
	public void shouldGetSizeOfHashSet() {
		// given
		String val1 = "Test Value 1";
		String val2 = "Test Value 2";
		String val3 = "Test Value 3";
		hashSet.add(val1);
		hashSet.add(val2);
		hashSet.add(val3);
		// when
		int size = hashSet.size();
		// then
		Assert.assertTrue(size == 3);
	}

	@Test
	public void shouldCheckOnContains() {
		// given
		String val1 = "Test Value 1";
		String val2 = "Test Value 2";
		String val3 = "Test Value 3";
		hashSet.add(val1);
		hashSet.add(val2);
		hashSet.add(val3);
		// when
		boolean contains = hashSet.contains(val2);
		boolean notContains = hashSet.contains("Test Value 0");
		// then
		Assert.assertTrue(contains);
		Assert.assertFalse(notContains);
	}

	@Test
	public void shouldCleanHashSet() {
		// given
		String val1 = "Test Value 1";
		String val2 = "Test Value 2";
		String val3 = "Test Value 3";
		hashSet.add(val1);
		hashSet.add(val2);
		hashSet.add(val3);
		// when
		hashSet.clear();
		// then
		Assert.assertTrue(hashSet.isEmpty());
	}

	@Test
	public void shouldCloneHashSet() {
		// given
		HashSet<String> hashSetCloned;
		String val1 = "Test Value 1";
		String val2 = "Test Value 2";
		String val3 = "Test Value 3";
		hashSet.add(val1);
		hashSet.add(val2);
		hashSet.add(val3);
		// when
		hashSetCloned = (HashSet<String>) hashSet.clone();
		// then
		Assert.assertEquals(hashSet, hashSetCloned);
	}

	@Test
	public void shouldBeIterable() {
		// given
		String[] vales = { "Test Value 1", "Test Value 2", "Test Value 3" };
		hashSet.add(vales[0]);
		hashSet.add(vales[1]);
		hashSet.add(vales[2]);
		int i = 0;
		// when
		Iterator<String> itr = hashSet.iterator();
		// then
		while (itr.hasNext()) {
			Assert.assertArrayEquals(itr.next().toCharArray(), hashSet.toArray()[i].toString().toCharArray());
			i++;
		}
	}
}
