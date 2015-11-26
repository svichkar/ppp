package com.nixsolutions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Rybkinrolla on 15.11.2015.
 */
public class CustomCollection<E> implements Collection<E> {
    private int size;
    private E[] initialArray;
    private CustomIterator<E> it;

    public CustomCollection() {
        initialArray = (E[]) new Object[]{};
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
        boolean result = false;
        if (o == null) {
            for (int i = 0; i < initialArray.length; i++) {
                if (initialArray[i] == o) {
                    result = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < initialArray.length; i++) {
                if (o.equals(initialArray[i])) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public CustomIterator<E> iterator() {
        return new CustomIterator<>();
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(initialArray, size);
    }

    @Override
    public boolean add(E object) {
        initialArray = Arrays.copyOf(initialArray, size + 1);
        initialArray[size] = object;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        it = this.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (o.equals(obj)) {
                it.remove();
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E[] tempArray = (E[]) c.toArray();
        int tempArrayLength = tempArray.length;
        initialArray = Arrays.copyOf(initialArray, size + tempArrayLength);
        System.arraycopy(tempArray, 0, initialArray, size, tempArrayLength);
        size += tempArrayLength;
        return tempArrayLength != 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            initialArray[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        it = this.iterator();
        boolean result = false;
        while (it.hasNext()) {
            Object obj = it.next();
            if (!c.contains(obj)) {
                it.remove();
                it.prev();
            }
            result = true;
        }
        return result;

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        it = this.iterator();
        boolean result = false;
        while (it.hasNext()) {
            Object obj = it.next();
            for (Object ob : c) {
                if (ob.equals(obj)) {
                    it.remove();
                    it.prev();
                    result = true;
                }
            }
        }
        return result;

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        for (Object o : c) {
            if (!this.contains(o)) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            a = (E[]) Array.newInstance(a.getClass().getComponentType(), size);
        } else if (a.length > size) {
            a[size] = null;
        }
        System.arraycopy(initialArray, 0, a, 0, size);
        return a;
    }

    public E get(int index) {
        return initialArray[index];
    }

    private class CustomIterator<E> implements Iterator<E> {
        private int cursor;

        private CustomIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (this.hasNext()) {
                return (E) CustomCollection.this.get(cursor++);
            } else {
                return null;
            }
        }

        @Override
        public void remove() {
            if (size - cursor > 0) {
                System.arraycopy(initialArray, cursor, initialArray, cursor - 1, size - cursor);
            }
            initialArray[--size] = null;
        }

        private E prev() {
            if (cursor > 0) {
                return (E) CustomCollection.this.get(cursor--);
            } else {
                return null;
            }
        }
    }


}
