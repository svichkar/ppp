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
    private final Object testObj = new Object();
    private final Object testObjOther = new Object();

    @After
    public void tearDown() {
        hashSet = null;
    }

    @Test
    public void shouldAddElementToHashSet() {
        hashSet = new HashSet();
        hashSet.add(testObj);
        assertTrue(hashSet.contains(testObj));
    }

    @Test
    public void shouldRemoveElementFromHashSet() {
        hashSet = new HashSet(java.util.Arrays.asList(testObj, testObjOther));
        hashSet.remove(testObj);
        assertFalse(hashSet.contains(testObj));
    }

    @Test
    public void shouldClearHashSet() {
        hashSet = new HashSet(java.util.Arrays.asList(testObj));
        HashSet hashSetNew = new HashSet();
        hashSet.clear();
        assertEquals(hashSet, hashSetNew);
    }

    @Test
    public void shouldContainUniqueValues() {
        hashSet = new HashSet(java.util.Arrays.asList(testObj, testObjOther, testObj, testObjOther));
        assertTrue(hashSet.size() == 2);
    }

    @Test
    public void shouldBeCorrectlyCloned() {
        hashSet = new HashSet(java.util.Arrays.asList(testObj, testObjOther));
        HashSet hashSetNew = (HashSet) hashSet.clone();
        assertEquals(hashSet, hashSetNew);
    }

    @Test
    public void shouldBeRightIterated() {
        hashSet = new HashSet(java.util.Arrays.asList(testObj, testObjOther));
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
        hashSet = new HashSet(java.util.Arrays.asList(testObj, testObjOther));
        assertTrue(hashSet.size() == 2);
    }

}
