package com.nixsolutions;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class HashSetTest {

    private HashSet hs = new HashSet();

    @Test
    public void shouldAddNullElement() {
        //given
        hs.add(null);
        //when
        int size = hs.size();
        //then
        Assert.assertEquals(1, size);
    }

    @Test
    public void shouldNotAddDuplicate() {
        //given
        hs.add(1);
        //when
        boolean isAddSimilarData = hs.add(1);
        //then
        Assert.assertEquals(1, hs.size());
        Assert.assertFalse(isAddSimilarData);
    }

    @Test
    public void shouldAddElements() {
        //given
        //when
        boolean addOne = hs.add(1);
        boolean addTwo = hs.add(2);
        int size = hs.size();
        //then
        Assert.assertTrue(addOne);
        Assert.assertTrue(addTwo);
        Assert.assertEquals(2, size);
    }

    @Test
    public void shouldClear() {
        //given
        hs.add(1);
        hs.add(2);
        hs.add(3);
        //when
        hs.clear();
        //then
        Assert.assertEquals(0, hs.size());
    }

    @Test
    public void shouldContainsAddedValue() {
        //given
        hs.add(1);
        //when
        boolean isContainsOne = hs.contains(1);
        //then
        Assert.assertTrue(isContainsOne);
    }

    @Test
    public void shouldReturnFalseIfValueIsNotContained() {
        //given
        hs.add(1);
        hs.add(2);
        //when
        boolean isContainsThird = hs.contains(3);
        boolean isContainsNull = hs.contains(null);
        //then
        Assert.assertFalse(isContainsThird);
        Assert.assertFalse(isContainsNull);
    }

    @Test
    public void shouldEmptyHashSetReturnTrueInIsEmpty() {
        //given
        //when
        boolean isEmpty = hs.isEmpty();
        //then
        Assert.assertTrue(isEmpty);
    }

    @Test
    public void shouldReturnTrueIfElementIsRemoved() {
        //given
        hs.add(1);
        //when
        boolean ret = hs.remove(1);
        //then
        Assert.assertTrue(ret);
    }

    @Test
    public void shouldReturnFalseIfElementIsNotExistWhichAreTryingToRemove() {
        //given
        hs.add(1);
        //when
        boolean ret = hs.remove(2);
        //then
        Assert.assertFalse(ret);
    }

    @Test
    public void shouldReturnSizeEqualsNumberOfAddedElements() {
        //given
        hs.add(1);
        hs.add(2);
        hs.add(3);
        //when
        int size = hs.size();
        //then
        Assert.assertEquals(3, size);
    }

}
