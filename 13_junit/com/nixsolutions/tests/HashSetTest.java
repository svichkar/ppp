package com.nixsolutions;

import org.junit.Before;
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
    private final Object testObject = new Object();
    private final Object testObjectOther = new Object();

    @Before
    public void initializeHashSet() {
        hashSet = new HashSet();
    }

    @Test
    public void shouldAddElementToHashSet() {
        hashSet.add(testObject);
        assertTrue(hashSet.contains(testObject));
    }

    @Test
    public void shouldRemoveElementFromHashSet() {
        hashSet = new HashSet(java.util.Arrays.asList(testObject, testObjectOther));
        hashSet.remove(testObject);
        assertFalse(hashSet.contains(testObject));
    }

    @Test
    public void shouldClearHashSet() {
        hashSet = new HashSet(java.util.Arrays.asList(testObject));
        HashSet hashSetNew = new HashSet();
        hashSet.clear();
        assertEquals(hashSet, hashSetNew);
    }

    @Test
    public void shouldContainUniqueValues() {
        hashSet = new HashSet(java.util.Arrays.asList(testObject, testObjectOther, testObject, testObjectOther));
        assertTrue(hashSet.size() == 2);
    }

    @Test
    public void shouldBeCorrectlyCloned() {
        hashSet = new HashSet(java.util.Arrays.asList(testObject, testObjectOther));
        HashSet hashSetNew = (HashSet) hashSet.clone();
        assertEquals(hashSet, hashSetNew);
    }

    @Test
    public void shouldBeRightIterated() {
        hashSet = new HashSet(java.util.Arrays.asList(testObject, testObjectOther));
        Iterator it = hashSet.iterator();
        int counter = 0;
        while(it.hasNext()){
            it.next();
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
        hashSet = new HashSet(java.util.Arrays.asList(testObject, testObjectOther));
        assertTrue(hashSet.size() == 2);
    }

}
