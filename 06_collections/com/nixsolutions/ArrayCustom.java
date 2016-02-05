package com.nixsolutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array based collection, supports optional methods to clear, add and remove
 * elements; implements the task from the lab 06_collections.
 */
public class ArrayCustom<E> implements Collection<E> {

    private Object[] array;

    public ArrayCustom() {
        array = new Object[0];
    }

    public ArrayCustom(Collection<? extends E> arg) {
        array = arg.toArray();
    }

    @Override
    public boolean add(E arg) {
        resize(1);
        int i = array.length - 1;
        array[i] = arg;
        if (array[i].equals(arg)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> arg) {
        int oldLength = array.length;
        resize(arg.size());
        Object[] argArray = arg.toArray();
        System.arraycopy(argArray, 0, array, oldLength, argArray.length);
        return argArray.length != 0;
    }

    /**
     * Resizes the array by received number of elements.
     * 
     * @param arg
     *            number of empty elements to add to the end of the array; if
     *            negative, this number of them will be trimmed from the end of
     *            the array.
     */
    private void resize(int arg) {
        int i = array.length + arg;
        int length = array.length < i ? array.length : i;
        if (i >= 0) {
            Object[] newArray = new Object[i];
            System.arraycopy(array, 0, newArray, 0, length);
            array = newArray;
        }
    }

    @Override
    public void clear() {
        array = new Object[0];
    }

    @Override
    public boolean contains(Object arg) {
        for (Object o : array) {
            if (o.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> arg) {
        for (Object o : arg) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorCustom();
    }

    /**
     * Removes a single element from the collection by removing the appropriate
     * element from the array, if the element equals to the object specified.
     * 
     * @param i
     *            Numerical index of the element in the array to compare and
     *            remove if equals to the object specified.
     * @param o
     *            Object to compare
     * @return Returns {@code true} if the collection has been changed
     */
    private boolean removeIfEquals(int i, Object o) {
        if (array[i].equals(o)) {
            System.arraycopy(array, i + 1, array, i, array.length - 1 - i);
            resize(-1);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        boolean changed = false;
        for (int i = 0; i < array.length; i++) {
            changed = removeIfEquals(i, o);
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            changed = remove(o);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (int i = 0; i < array.length; i++) {
            for (Object o : c) {
                changed = removeIfEquals(i, o);
            }
        }
        return changed;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    @Override
    public <T> T[] toArray(T[] argArray) {
        if (argArray.length < array.length) {
            return (T[]) Arrays.copyOf(array, array.length);
        } else {
            System.arraycopy(array, 0, argArray, 0, array.length);
            if (argArray.length > array.length) {
                argArray[array.length] = null;
            }
            return (T[]) argArray;
        }

    }

    /**
     * Iterator implemented as inner class according to the task from the lab
     * 06_collections.
     */
    private class IteratorCustom implements Iterator<E> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor != array.length;
        }

        @Override
        public E next() {
            if (this.hasNext()) {
                int current = cursor;
                cursor++;
                return (E) array[current];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
