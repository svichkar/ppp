package com.nixsolutions;

import java.util.*;
import java.util.Arrays;

/**
 * Created by Sergey on 13.11.2015.
 */
public class MyCollection<E> implements Collection<E> {
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private Iterator<E> iterator;
    private Iterator<?> externalIterator;
    private int size;

    public MyCollection() {
        this.elementData = EMPTY_ELEMENTDATA;
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
        iterator = this.iterator();
        if (o == null) {
            for(E temp:this) {
                if (temp == null) {
                    return true;
                }
            }
        } else {
            for(E temp:this) {
                if (o.equals(temp)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public Object[] toArray() {
        return java.util.Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) java.util.Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        elementData = java.util.Arrays.copyOf(elementData, size + 1);
        elementData[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        iterator = this.iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    iterator.remove();
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (o.equals(iterator.next())) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        externalIterator = c.iterator();
        while (externalIterator.hasNext()) {
            if (!this.contains(externalIterator.next())) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        externalIterator = c.iterator();
        while (externalIterator.hasNext()) {
            this.add((E) externalIterator.next());
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        iterator = this.iterator();
        while (iterator.hasNext()) {
            if (c.contains(iterator.next())) {
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public void clear() {
        iterator = this.iterator();
        if (size > 0) {
            Arrays.fill(elementData, 0, size, null);
            size = 0;
        }
    }

    public E get(int position) {
        if (position < this.size) {
            return (E) elementData[position];
        } else {
            throw new NoSuchElementException("No such element in collection");
        }
    }

    private class MyIterator<E> implements Iterator<E> {
        int counter;

        public MyIterator(){
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            return (counter < MyCollection.this.size);
        }

        @Override
        public E next() throws NoSuchElementException {
            if (this.hasNext()) {
                return (E) MyCollection.this.get(counter++);
            } else {
                throw new NoSuchElementException("Out of bound");
            }
        }

        @Override
        public void remove() throws IllegalStateException {
            if (size - counter > 0) {
                System.arraycopy(elementData, counter, elementData, counter - 1, size - counter);
            }
            elementData[--size] = null;
            counter--;
        }
    }
}
