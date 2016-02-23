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
    public void shouldAddElement() {
        //given
        //when
        boolean addOne = hs.add(1);
        boolean addTwo = hs.add(2);
        int size = hs.size();
        //then
        Assert.assertEquals(true, addOne);
        Assert.assertEquals(true, addTwo);
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
        Assert.assertEquals(true, isContainsOne);
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
        Assert.assertEquals(false, isContainsThird);
        Assert.assertEquals(false, isContainsNull);
    }

    @Test
    public void shouldThatInitHashSetIsEmpty() {
        //given
        //when
        boolean isEmpty = hs.isEmpty();
        //then
        Assert.assertEquals(true, isEmpty);
    }

    @Test
    public void shouldReturnTrueIfElementIsRemoved() {
        //given
        hs.add(1);
        //when
        boolean ret = hs.remove(1);
        //then
        Assert.assertEquals(true, ret);
    }

    @Test
    public void shouldReturnFalseIfElementIsNotExistWhichAreTryingToRemove() {
        //given
        hs.add(1);
        //when
        boolean ret = hs.remove(2);
        //then
        Assert.assertEquals(false, ret);
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
