package com.nixsolutions;

import java.util.*;

public class CustomCollection<E> implements Collection<E> {

    private E[] array;
    private int size = 0;
    private int capacity = 10;

    public CustomCollection() {
        array = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public boolean add(E o) {
        //overflow check
        if (size == capacity) {
            capacity *= 2;
            this.array = java.util.Arrays.copyOf(array, capacity);
        }
        array[size++] = o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[size--] = null;
                return true;
            }
        }
        return false;
    }

    public boolean remove(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index '" + index + "' out of the bounds size");
        }
        for (int j = index; j < size - 1; j++) {
            array[j] = array[j + 1];
        }
        array[size--] = null;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object o : c.toArray()) {
            add((E) o);
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        capacity = 10;
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean retainAll(Collection c) {
        Object[] arrayForDelete = new Object[size];
        int innerSize = 0;
        boolean retFlag = false;

        //forming array for delete
        for (int i = 0; i < size; i++) {
            if (!c.contains(array[i])) {
                arrayForDelete[innerSize] = array[i];
                innerSize++;
                retFlag = true;
            }
        }
        //remove element
        for (Object o : arrayForDelete) {
            remove(o);
        }
        return retFlag;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean retFlag = false;
        for (Object o : c) {
            retFlag = true;
            remove(o);
        }
        return retFlag;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return (E[]) java.util.Arrays.copyOf(array, size, a.getClass());
    }

    @Override
    public E[] toArray() {
        return java.util.Arrays.copyOf(array, size);
    }

    /**
     * Inner class MyIterator realized interface {@link Iterator}
     * for {@link CustomCollection} class.
     */
    class MyIterator implements Iterator<E> {

        private int currentPos = -1;

        @Override
        public boolean hasNext() {
            if (currentPos < size - 1) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            if (hasNext()) {
                return array[++currentPos];
            }
            return null;
        }
    }

}
