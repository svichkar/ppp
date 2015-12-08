package com.nixsolutions;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class HashSetTest {
	private HashSet<String> testHashSet1;

	@Before
	public void initialization() {
		testHashSet1 = new HashSet<String>();
	}

	@Test
	public void shouldAddSingleObject() {
		testHashSet1.add("one object");
		assertTrue(testHashSet1.contains("one object"));
	}

	@Test
	public void shouldCreateEmptyHashSet() {
		HashSet<String> hashSet = new HashSet<String>();
		assertTrue(hashSet.isEmpty());
	}

	@Test
	public void shouldBeEmptyAfterClearing() {
		int n = 5;
		for (int i = 0; i < n; i++) {
			testHashSet1.add("Object" + i);
		}
		testHashSet1.clear();

		assertTrue(testHashSet1.isEmpty());
	}

	@Test
	public void shouldChangeSizeAfterAddingElements() {
		int size = 10;
		for (int i = 0; i < size; i++) {
			testHashSet1.add("Object" + i);
		}

		assertTrue(testHashSet1.size() == size);
	}

	@Test
	public void shouldRemoveSpecifiedString() {
		testHashSet1.add("Object 1");
		testHashSet1.add("Object 2");
		testHashSet1.add("Object 3");
		testHashSet1.remove("Object 1");

		assertFalse("Object 1 should be removed", testHashSet1.contains("Object 1"));
	}

	@Test
	public void shouldBeEqualToOtherSet() {
		testHashSet1.add("Object1");
		testHashSet1.add("Object2");
		testHashSet1.add("Object3");

		HashSet<String> testHashSet2 = new HashSet<>();
		testHashSet2.add("Object1");
		testHashSet2.add("Object2");
		testHashSet2.add("Object3");

		assertTrue(testHashSet1.equals(testHashSet2));
	}

	@Test
	public void shouldAddAllElementsFromOtherHashSetExceptPresenting() {
		testHashSet1.add("object1");
		testHashSet1.add("object2");
		testHashSet1.add("object3");
		testHashSet1.add("object4");

		HashSet<String> testHashSet2 = new HashSet<>();
		testHashSet2.add("object1");
		testHashSet2.add("object4");
		testHashSet2.add("object5");

		testHashSet1.addAll(testHashSet2);
		assertTrue(testHashSet1.size() == 5 && testHashSet1.contains("object5"));
	}

}
