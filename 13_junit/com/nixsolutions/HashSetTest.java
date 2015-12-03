package com.nixsolutions;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by rybkinrolla on 02.12.2015.
 */
public class HashSetTest {
    private HashSet hashSet;
    private final Object testString1 = new String("testString1");
    private final Object testString2 = new String("testString2");

    @After
    public void tearDown() {
        hashSet = null;
    }

    @Test
    public void shouldAddElementToHashSet() {
        hashSet = new HashSet();
        hashSet.add(testString1);
        assertTrue(hashSet.contains(testString1));
    }

    @Test
    public void shouldRemoveElementFromHashSet() {
        hashSet = new HashSet(java.util.Arrays.asList(testString1, testString2));
        hashSet.remove(testString1);
        assertFalse(hashSet.contains(testString1));
    }

    @Test
    public void shouldClearHashSet() {
        hashSet = new HashSet(java.util.Arrays.asList(testString1));
        HashSet hashSetNew = new HashSet();
        hashSet.clear();
        assertEquals(hashSet, hashSetNew);
    }

    @Test
    public void shouldContainUniqueValues() {
        hashSet = new HashSet(java.util.Arrays.asList(testString1, testString2, testString1, testString2));
        assertTrue(hashSet.size() == 2);
    }

    @Test
    public void shouldBeCorrectlyCloned() {
        hashSet = new HashSet(java.util.Arrays.asList(testString1, testString2));
        HashSet hashSetNew = (HashSet) hashSet.clone();
        assertEquals(hashSet, hashSetNew);
    }

    @Test
    public void shouldBeRightIterated() {
        hashSet = new HashSet(java.util.Arrays.asList(testString1, testString2));
        Iterator it = hashSet.iterator();
        int counter = 0;
        Object obj;
        while(it.hasNext()){
            obj = it.next();
            counter++;
        }
        assertEquals(counter,2);
    }

    @Test
    public void shouldBeEmptyAfterCreated() {
        hashSet = new HashSet();
        assertTrue(hashSet.isEmpty());
    }

    @Test
    public void shouldBeRightSized() {
        hashSet = new HashSet(java.util.Arrays.asList(testString1, testString2));
        assertTrue(hashSet.size() == 2);
    }

}
