package com.nixsolutions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;

/**
 * Created by rybkinrolla on 02.12.2015.
 */
public class HashSetClass {
    private HashSet hashSet;

    @Before
    public void initiateHashSet(){
        hashSet = new HashSet();
    }

    @Test
    public void isHashSetEmpty(){
        assertTrue(hashSet.isEmpty());
    }

    @Test
    public void isNotHashSetEmpty(){
        assertFalse(hashSet.isEmpty());
    }
}
