package com.nixsolutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sobolenko on 2/1/2016.
 */
public class NewCollections<E> implements Collection<E> {
    Object[] initArray = new Object[0];
    Object[] resultArray = {};
    private int currentPosition = -1;
    public int size;

    public NewCollections() {
        clear();
    }

    /**
     * @return size of collection
     */
    public int size() {
        double capasity = 0;
        for (Object obj : resultArray) {
            capasity++;
        }
        if (capasity > Integer.MAX_VALUE) {
            capasity = Integer.MAX_VALUE;
        } else {
            size = (int) capasity;
        }
        return (int) capasity;
    }

    /**
     * Check that collection is empty
     *
     * @return true if empty, false if not empty
     */
    public boolean isEmpty() {
        double capasity = 0;
        if (resultArray.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if this collection contains the specified element.
     *
     * @param o element whose presence in this collection
     * @return true if this collection contains the element
     */
    public boolean contains(Object o) {
        for (Object obj : resultArray) {
            if (obj.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return
     */
    public Iterator iterator() {
        return new NewIterator();
    }

    /**
     * Convert collection to array
     *
     * @return array
     */
    public Object[] toArray() {
        Object[] newArray = new Object[resultArray.length];
        System.arraycopy(resultArray, 0, newArray, 0, resultArray.length);
        return newArray;
    }

    /**
     * Appends the element to the end of this collection.
     *
     * @param o element to be appended
     * @return true if colletion changed
     */
    public boolean add(Object o) {
        size();
        for (Object obj : initArray) {
            if (obj.equals(o)) {
                return false;
            }
        }
        try {
            resultArray = Arrays.copyOf(resultArray, resultArray.length + 1);
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError();
        }
        resultArray[size] = o;
        size();
        return true;
    }

    /**
     * Remove the element to the end of this collection.
     *
     * @param o element to be removed
     * @return true if colletion changed
     */
    public boolean remove(Object o) {
        size();
        Object[] tempArray = new Object[resultArray.length - 1];
        boolean result = false;
        boolean flag = false;
        int it = 0;
        int cap = 0;
        if (!contains(o)) {
            return false;
        }
        for (Object obj : resultArray) {
            if (obj.equals(o)) {
                for (Object object : resultArray) {
                    if (cap != it || flag) {
                        tempArray[cap] = object;
                        result = true;
                        cap++;
                    } else {
                        flag = true;
                    }
                }
            }
            it++;
        }
        resultArray = Arrays.copyOf(tempArray, tempArray.length);
        size();
        return result;
    }

    /**
     * Appends all elements to the end of this collection.
     *
     * @param c collection with element to be added
     * @return true if successful (collection changed)
     */
    public boolean addAll(Collection c) {
        Object[] newArray = c.toArray();
        int oldHash = Arrays.hashCode(resultArray);
        int oldLength = resultArray.length;
        try {
            resultArray = Arrays.copyOf(resultArray, resultArray.length + newArray.length);
        } catch (OutOfMemoryError error) {
            throw new OutOfMemoryError();
        }
        System.arraycopy(newArray, 0, resultArray, oldLength, newArray.length);
        int newHash = Arrays.hashCode(resultArray);
        size();
        if (oldHash != newHash) {
            return true;
        }
        return false;
    }

    /**
     * erase all elements
     */
    public void clear() {
        resultArray = Arrays.copyOf(resultArray, 0);
        size();
    }

    /**
     * Save only elements that are contained in the specified collection
     *
     * @param c specified collection
     * @return true if collection changed
     */
    public boolean retainAll(Collection c) {
        Object[] array = toArray(resultArray);
        int oldHash = Arrays.hashCode(resultArray);
        for (int i = 0; i < size; i++) {
            if (c.contains(array[i])) {
                remove(array[i]);
            }
        }
        int newHash = Arrays.hashCode(resultArray);
        size();
        if (oldHash != newHash) {
            return true;
        }
        return false;
    }

    /**
     * Remove only elements that are contained in the specified collection
     *
     * @param c specified collection
     * @return true if collection changed
     */
    public boolean removeAll(Collection c) {
        Object[] newArray = c.toArray();
        int oldHash = Arrays.hashCode(resultArray);
        for (Object x : newArray) {
            remove(x);
        }
        size();
        int newHash = Arrays.hashCode(resultArray);
        if (oldHash != newHash) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if this collection contains the specified collection.
     *
     * @param c specified collection
     * @return
     */
    public boolean containsAll(Collection c) {

        boolean result = false;
        for (Object x : c) {
            if (contains(x)) {
                result = true;
            } else {
                return false;
            }
        }
        return result;
    }

    /**
     * Convert collection to array
     *
     * @param a   array
     * @param <T>
     * @return specified array
     */
    public <T> T[] toArray(T[] a) {
        T[] array = (T[]) new Object[resultArray.length];
        for (int i = 0; i < resultArray.length; i++) {
            array[i] = (T) resultArray[i];
        }
        return array;
    }

    //--------------------------------------------------------------
    public class NewIterator implements Iterator {
        /**
         * True if next element exist
         *
         * @return
         */
        public boolean hasNext() {
            if (currentPosition != size - 1) {
                return true;
            }
            return false;
        }

        /**
         * @return next element
         */
        public Object next() {
            if (currentPosition >= size)
                throw new NoSuchElementException();
            currentPosition++;
            return resultArray[currentPosition];
        }

        /**
         * remove current element
         */
        public void remove() {
            if (currentPosition >= size)
                throw new NoSuchElementException();
            Object[] tempArray = new Object[resultArray.length - 1];
            boolean flag = false;
            int it = 0;
            int cap = 0;
            for (Object obj : resultArray) {
                if (obj.equals(resultArray[currentPosition])) {
                    for (Object object : resultArray) {
                        if (cap != it || flag) {
                            tempArray[cap] = object;
                            cap++;
                        } else {
                            flag = true;
                        }
                    }
                }
                it++;
            }
            resultArray = Arrays.copyOf(tempArray, tempArray.length);
            size();
        }
    }
}