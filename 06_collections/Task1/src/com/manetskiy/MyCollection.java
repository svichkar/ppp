package com.manetskiy;

import java.util.*;

public class MyCollection<T> implements Collection<T> {

    private Object[] objects;
    private int currentSize = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public MyCollection() {
        objects = new Object[DEFAULT_CAPACITY];
    }

    public MyCollection(int customCapacity) {
        objects = new Object[customCapacity];
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private class Iteration implements Iterator<T> {

        private int position = 0;
        private int cursor = -1;

        @Override
        public boolean hasNext() {
            return position < currentSize;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            position++;
            cursor = position - 1;
            if (position > currentSize)
                throw new NoSuchElementException();
            return (T) objects[cursor];
        }

        @Override
        public void remove() {
            if (cursor == (position - 1)) {
                System.arraycopy(objects, cursor + 1, objects, cursor, objects.length - 1 - cursor);
                currentSize--;
                position--;
                cursor++;
            } else
                throw new IllegalStateException();
        }
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < currentSize; i++) {
            if (objects[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iteration();
    }

    @Override
    public Object[] toArray() {
        Object[] toReturn = new Object[currentSize];
        for (int i = 0; i < currentSize; i++) {
            toReturn[i] = objects[i];
        }
        return toReturn;
    }

    @Override
    public boolean add(Object o) {
        currentSize++;
        if (objects.length <= currentSize) {
            Object[] newObjects = new Object[objects.length + 10];
            System.arraycopy(objects, 0, newObjects, 0, objects.length - 1);
            objects = newObjects;
            objects[currentSize - 1] = o;
        } else {
            objects[currentSize - 1] = o;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < currentSize; i++) {
            if (o.equals(objects[i])) {
                System.arraycopy(objects, i + 1, objects, i, objects.length - 1 - i);
                currentSize--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        boolean isSuccessful = false;
        if (this.equals(c))
            isSuccessful = false;
        else {
            for (Object ob : c) {
                if (this.add(ob)) isSuccessful = true;
            }
        }
        return isSuccessful;
    }

    @Override
    public void clear() {
        objects = new Object[DEFAULT_CAPACITY];
        currentSize = 0;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean isModified = false;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                isModified = true;
            }
        }
        return isModified;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean isRemove = false;
        for (Object ob : c) {
            if (this.remove(ob)) isRemove = true;
            else return false;
        }
        return isRemove;
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean isContain = false;
        for (Object ob : c) {
            if (contains(ob)) {
                isContain = true;
            } else return false;
        }
        return isContain;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(Object[] a) {
        System.arraycopy(objects, 0, a, 0, currentSize);
        return (T[]) a;
    }
}
