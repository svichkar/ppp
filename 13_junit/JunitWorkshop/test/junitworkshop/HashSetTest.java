/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junitworkshop;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    public HashSetTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.testHashSet = new HashSet();
    }

    @After
    public void tearDown() {
        this.testHashSet = null;
    }

    @Test
    public void addShouldAddNewElementToTheHashTableIfItIsnotAlreadyPresent() {
        this.testHashSet.add(this.TEST_STRING1);
        this.testHashSet.add(this.TEST_STRING1);
        this.testHashSet.add(this.TEST_INTEGER);
        this.testHashSet.add(this.TEST_INTEGER);
        this.testHashSet.add(null);
        assertTrue(this.testHashSet.containsAll(new HashSet(Arrays.asList(this.TEST_STRING1, 
                this.TEST_INTEGER, null))));
        assertTrue(this.testHashSet.size() == 3);

    }

    @Test
    public void addShouldReturnTrueIfElementWasAdded() {
        assertTrue(this.testHashSet.add(this.TEST_STRING1));

    }

    @Test
    public void addShouldReturnFalseIfElementWasNotAdded() {
        this.testHashSet.add(this.TEST_STRING1);
        assertFalse(this.testHashSet.add(this.TEST_STRING1));

    }

    @Test
    public void removeShouldRemoveTheOneObjectFromHashSet() {
        this.testHashSet = new HashSet(Arrays.asList(this.TEST_INTEGER, null, this.TEST_STRING1, 
                this.TEST_STRING2));
        this.testHashSet.remove(this.TEST_STRING1);
        assertTrue(this.testHashSet.containsAll(new HashSet(Arrays.asList(this.TEST_INTEGER, null, 
                this.TEST_STRING2))));
        assertFalse(this.testHashSet.contains(this.TEST_STRING1));
    }

    @Test
    public void removeShouldReturnTrueIfElementWasRemoved() {
        this.testHashSet = new HashSet(Arrays.asList(this.TEST_INTEGER, null, this.TEST_STRING1, 
                this.TEST_STRING2));
        assertTrue(this.testHashSet.remove(this.TEST_STRING1));
    }

    @Test
    public void removeShouldReturnFalseIfElementWasNotRemoved() {
        this.testHashSet = new HashSet(Arrays.asList(this.TEST_INTEGER, null, this.TEST_STRING1, 
                this.TEST_STRING2));
        assertFalse(this.testHashSet.remove("String5"));
    }

    @Test
    public void sizeShouldReflectActualHashSetSize() {
        this.testHashSet = new HashSet(Arrays.asList(this.TEST_INTEGER, null, this.TEST_STRING1));
        assertEquals(this.testHashSet.size(), 3);
    }

    @Test
    public void sizeShouldBeZeroForNewHashtable() {
        assertEquals(this.testHashSet.size(), 0);
    }

    @Test
    public void containsShouldReturnTrueIfElementIsPresent() {
        this.testHashSet = new HashSet(Arrays.asList(this.TEST_INTEGER, null, this.TEST_STRING1));
        assertTrue(this.testHashSet.contains(this.TEST_STRING1));

    }

    @Test
    public void containsShouldReturnFalseIfElementIsNotPresent() {
        this.testHashSet = new HashSet(Arrays.asList(this.TEST_INTEGER, null, this.TEST_STRING1));
        assertFalse(this.testHashSet.contains(this.TEST_STRING2));

    }
}
