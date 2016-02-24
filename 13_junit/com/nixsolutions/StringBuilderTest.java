package com.nixsolutions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Implements the task of variant 2 of 13_junit lab */
public class StringBuilderTest {
    StringBuilder stringBuilder;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        stringBuilder = new StringBuilder("abcdefghijklmnopqrstuvwxyz0123456789aAa");
    }

    @Test
    public void appendsToEndOfSequence() {
        // given
        stringBuilder = new StringBuilder();
        char c = 'a';
        double d = 0.111234009265899;
        int i = 192;
        String string = "test";
        // when
        stringBuilder.append(c).append(d).append(i).append(string);
        // then
        assertEquals("a0.111234009265899192test", stringBuilder.toString());
    }

    @Test
    public void removesCharsInSubstringOfSequence() {
        // when
        stringBuilder.delete(1, 35);
        // then
        assertEquals("a9aAa", stringBuilder.toString());
    }

    @Test
    public void failsToRemoveSubstringWithInvalidIndex() {
        assertDeleteInvalidIndex(-1, 1);
        assertDeleteInvalidIndex(40, 41);
        assertDeleteInvalidIndex(21, 12);
    }

    private void assertDeleteInvalidIndex(int start, int end) {
        try {
            stringBuilder.delete(start, end);
            if (start > end) {
                fail("delete accepted unexpected indexes: start " + start + " > end " + end);
            } else {
                fail("delete accepted unexpected start index: " + start);
            }

        } catch (IndexOutOfBoundsException e) {
            // Exception is expected in this case, so doing nothing.
        }
    }

    @Test
    public void returnsIndexOfFirstOccurenceOfSubstringFromIndex() {
        assertSame(25, stringBuilder.indexOf("z0", 0));
        assertSame(0, stringBuilder.indexOf("a", 0));
        assertSame(36, stringBuilder.indexOf("a", 1));
        assertSame(38, stringBuilder.indexOf("a", 37));
    }

    @Test
    public void returnsNegativeOneIfStringNotFound() {
        assertSame(-1, stringBuilder.indexOf("!@#$", 0));
    }

    @Test
    public void failsToReturnIndexOfNullString() throws NullPointerException {
        exception.expect(NullPointerException.class);
        stringBuilder.indexOf(null);
    }

    @Test
    public void insertsToIndicatedOffset() {
        // given
        char[] ca = { 'z', 'U', '2', '!' };
        float f = 0.7684921F;
        int i = 0;
        String string = null;
        // when
        stringBuilder.insert(27, ca).insert(43, f).insert(10, i).insert(0, string);
        // then
        String expected = "nullabcdefghij0klmnopqrstuvwxyz0zU2!123456789aAa0.7684921";
        assertEquals(expected, stringBuilder.toString());
    }

    @Test
    public void failsToInsertAtInvalidIndex() {
        assertInsertAtInvalidIndex(40, "test1");
        assertInsertAtInvalidIndex(-1, "test2");
    }

    private void assertInsertAtInvalidIndex(int i, String string) {
        try {
            stringBuilder.insert(i, string);
            fail("insert should not accept [" + i + "]");
        } catch (IndexOutOfBoundsException e) {
            // Exception is expected in this case, so doing nothing.
        }
    }

    @Test
    public void reversesCharacterSequence() {
        // when
        stringBuilder.reverse();
        // then
        assertEquals("aAa9876543210zyxwvutsrqponmlkjihgfedcba", stringBuilder.toString());
    }

    @Test
    public void returnsSubstringBetweenIndexes() {
        assertEquals("z01234", stringBuilder.substring(25, 31));
    }

    @Test
    public void failsToReturnSubstringWithInvalidIndexes() {
        assertSubstringAtInvalidIndexes(-1, 1);
        assertSubstringAtInvalidIndexes(-2, -1);
        assertSubstringAtInvalidIndexes(1, -2);
        assertSubstringAtInvalidIndexes(21, 41);
        assertSubstringAtInvalidIndexes(41, 300);
        assertSubstringAtInvalidIndexes(35, 4);
    }

    private void assertSubstringAtInvalidIndexes(int start, int end) {
        try {
            stringBuilder.substring(start, end);
            fail("substring accepted unexpected indexes: start [" + start + "], end [" + end + "]");
        } catch (IndexOutOfBoundsException e) {
            // Exception is expected in this case, so doing nothing.
        }
    }

}
