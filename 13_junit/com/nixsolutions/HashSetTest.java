package com.nixsolutions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;

/**
 * Created by rybkinrolla on 02.12.2015.
 */
public class HashSetTest {
    private HashSet hashSet;
    private final String testString1 = "testString1";
    private final String testString2 = "testString2";

    @Before
    public void initiateHashSet(){
        hashSet = new HashSet();
    }
    @After
    public void tearDown(){
        hashSet = null;
    }
    @Test
    public void shouldAddElementToHashSet(){
        hashSet.add(testString1);
        assertTrue(hashSet.contains(testString1));
    }
    @Test
    public void shouldRemoveElementFromHashSet(){
        hashSet = new HashSet(java.util.Arrays.asList(testString1,testString2));
        hashSet.remove(testString1);
        assertFalse(hashSet.contains(testString1));
    }
    @Test
    public void shouldClearHashSet(){
        hashSet = new HashSet(java.util.Arrays.asList(testString1));
        HashSet hashSetNew = new HashSet();
        hashSet.remove(testString1);
        assertTrue(hashSet.equals(hashSetNew));
    }
    @Test
    public void test1(){

    }
    @Test
    public void test1(){

    }
    @Test
    public void test1(){

    }

}
