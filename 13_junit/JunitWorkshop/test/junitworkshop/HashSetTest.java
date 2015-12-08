/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junitworkshop;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mednorcom
 */
public class HashSetTest {

    private final String TEST_STRING1 = "String1";
    private final String TEST_STRING2 = "String2";
    private final Integer TEST_INTEGER = 10;
    private HashSet testHashSet;

    @Before
    public void setUp() {
        testHashSet = new HashSet();
    }

    @Test
    public void methodAddShouldAddNewElementToTheHashTableIfItIsnotAlreadyPresent() {
        testHashSet.add(TEST_STRING1);
        testHashSet.add(TEST_STRING1);
        testHashSet.add(TEST_INTEGER);
        testHashSet.add(TEST_INTEGER);
        testHashSet.add(null);

        assertTrue(testHashSet.contains(TEST_STRING1));
        assertTrue(testHashSet.contains(TEST_INTEGER));
        assertTrue(testHashSet.contains(null));
        assertEquals(3,testHashSet.size());

    }

    @Test
    public void addShouldReturnTrueIfElementWasAdded() {
        assertTrue(testHashSet.add(TEST_STRING1));

    }

    @Test
    public void addShouldReturnFalseIfElementWasNotAdded() {
        testHashSet.add(TEST_STRING1);
        assertFalse(testHashSet.add(TEST_STRING1));

    }

    @Test
    public void removeShouldRemoveTheOneObjectFromHashSet() {
        testHashSet.add(TEST_STRING1);
        testHashSet.add(TEST_STRING2);
        testHashSet.add(null);
        testHashSet.add(TEST_INTEGER);
        testHashSet.remove(TEST_STRING1);
        assertTrue(testHashSet.contains(TEST_STRING2));
        assertTrue(testHashSet.contains(TEST_INTEGER));
        assertTrue(testHashSet.contains(null));
        assertFalse(testHashSet.contains(TEST_STRING1));
    }

    @Test
    public void removeShouldReturnTrueIfElementWasRemoved() {
        testHashSet.add(TEST_STRING1);
        testHashSet.add(TEST_STRING2);
        testHashSet.add(null);
        testHashSet.add(TEST_INTEGER);
        assertTrue(testHashSet.remove(TEST_STRING1));
    }

    @Test
    public void removeShouldReturnFalseIfElementWasNotRemoved() {
        testHashSet.add(TEST_STRING1);
        testHashSet.add(null);
        testHashSet.add(TEST_INTEGER);
        assertFalse(testHashSet.remove(TEST_STRING2));
    }

    @Test
    public void sizeShouldReflectActualHashSetSize() {
        testHashSet.add(TEST_STRING1);
        testHashSet.add(null);
        testHashSet.add(TEST_INTEGER);
        assertEquals(testHashSet.size(), 3);
    }

    @Test
    public void sizeShouldBeZeroForNewHashtable() {
        assertEquals(testHashSet.size(), 0);
    }

    @Test
    public void containsShouldReturnTrueIfElementIsPresent() {
        testHashSet.add(TEST_STRING1);
        testHashSet.add(null);
        testHashSet.add(TEST_INTEGER);
        assertTrue(testHashSet.contains(TEST_STRING1));

    }

    @Test
    public void containsShouldReturnFalseIfElementIsNotPresent() {
        testHashSet.add(TEST_STRING1);
        testHashSet.add(null);
        testHashSet.add(TEST_INTEGER);
        assertFalse(testHashSet.contains(TEST_STRING2));

    }
}
