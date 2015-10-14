package com.nixsolutions;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

public class HashSetTest {

    HashSet<String> hs;

    @Before
    public void initializeVariableHashSet() {
	hs = new HashSet<>();

    }

    @Test
    public void elementShouldBeAddedToHashSet() {

	hs.add("FirstElement");
	assertTrue(hs.contains("FirstElement"));

    }

    @Test
    public void elementShouldBeDeketedFromHashSet() {

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
	Iterator<String> iterator = hs.iterator();
	Iterator<String> iteratorTwo = hs.iterator();
	int counterI = 0;
	int counterJ = 0;
	while (iterator.hasNext()) {
	    String levelOneVar = iterator.next();
	    while (iteratorTwo.hasNext()) {
		String levelTwoVar = iteratorTwo.next();
		if (counterI != counterJ && levelOneVar.equals(levelTwoVar)) {
		    assertFalse(true);
		    break;
		}

		counterJ++;
	    }
	    counterI++;
	}

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
