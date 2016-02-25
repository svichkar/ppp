package com.manetskiy;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Iterator;

public class TestHashSet {

    private HashSet<Integer> set;

    @Before
    public void setUp() {
        set = new HashSet<Integer>() {{add(10);}{add(20);}{add(30);}};
    }

    @Test
    public void shouldAddElementThatIsNotAlreadyPresent() {
        assertTrue(set.add(50));
    }

    @Test
    public void shouldNotAddElementThatAlreadyPresent() {
        assertFalse(set.add(20));
    }

    @Test
    public void shouldAddNull() {
        set.add(null);
        assertTrue(set.contains(null));
    }

    @Test
    public void shouldRemoveAllElementsFromSet() {
        set.clear();
        assertEquals(0, set.size());
    }

    @Test
    public void shouldCloneSet() {
        Object ob = set.clone();
        HashSet<Integer> set2 = (HashSet<Integer>) ob;
        assertTrue(set2.equals(set));
    }

    @Test
    public void shouldCheckIfSetContainsSpecifiedElement() {
        assertTrue(set.contains(20));
        assertFalse(set.contains(100000));
    }

    @Test
    public void shouldRemoveSpecifiedElement() {
        assertTrue(set.remove(20));
    }

    @Test
    public void shouldIterateOverTheElements() {
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer a = iterator.next();
            assertTrue(set.contains(a));
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionShouldBeThrownIfModifySetWhileIterating() {
        thrown.expect(java.util.ConcurrentModificationException.class);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            set.add(20000);
        }
    }

}
