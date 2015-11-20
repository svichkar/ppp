package com.nixsolutions;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Sergey on 13.11.2015.
 */
public class MyCollection<E> implements Collection<E> {
    private E[] array = null;
    private E[] temporaryArray = null;
    private boolean flag = false;
    private Iterator iterator;

    @Override
    public int size() throws NullPointerException {
        if (!this.isEmpty()) {
            return array.length;
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return (array == null);
    }

    @Override
    public boolean contains(Object o) throws ClassCastException, NullPointerException {
        flag = false;
        try {
            iterator = this.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(o)) {
                    flag = true;
                }
            }
        } catch (ClassCastException e) {
            throw e;
        }
        return flag;
    }

    @Override
    public MyIterator iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() throws NoSuchElementException {
        if (!this.isEmpty()) {
            Object[] temp = new Object[this.size()];
            iterator = this.iterator();
            try {
                for (int i = 0; i < array.length; i++) {
                    temp[i] = iterator.next();
                }
                return temp;
            } catch (NoSuchElementException e) {
                throw e;
            }
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    @Override
    public boolean add(E o) {
        if (!this.isEmpty()) {
            temporaryArray = (E[]) new Object[array.length + 1];
            System.arraycopy(array, 0, temporaryArray, 0, array.length);
            temporaryArray[array.length] = o;
            array = temporaryArray;
        } else {
            array = (E[]) new Object[]{o};
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!this.isEmpty()) {
            int position = 0;
            for (int i = 0; i < this.size(); i++) {
                if (this.getElementAt(i).equals(o)) {
                    position = i;
                }
            }
            temporaryArray = (E[]) new Object[array.length - 1];
            System.arraycopy(array, 0, temporaryArray, 0, position);
            System.arraycopy(array, position + 1, temporaryArray, position, array.length - position - 1);
            array = temporaryArray;
            return true;
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    @Override
    public boolean addAll(Collection c) {
        if (!c.isEmpty()) {
            iterator = c.iterator();
            for (int i = 0; i < c.size(); i++) {
                this.add((E) iterator.next());
            }
        } else {
            throw new NullPointerException("Input collection is empty");
        }
        return true;
    }

    @Override
    public void clear() {
        if (!this.isEmpty()) {
            array = null;
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    @Override
    public boolean retainAll(Collection c) {
        iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        iterator = this.iterator();
        while (iterator.hasNext()) {
            if (c.contains(iterator.next())) {
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        flag = true;
        if (!c.isEmpty()) {
            iterator = c.iterator();
            while (iterator.hasNext()) {
                if (!this.contains(iterator.next())) {
                    flag = false;
                }
            }
        } else {
            throw new NullPointerException("Input collection is empty");
        }
        return flag;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (!this.isEmpty()) {
            int i = 0;
            iterator = this.iterator();
            if (a.length < this.size()) {
                a = (T[]) new Object[this.size()];
                while (iterator.hasNext()) {
                    a[i] = (T) iterator.next();
                    i++;
                }
            } else {
                while (iterator.hasNext()) {
                    a[i] = (T) iterator.next();
                    i++;
                }
                for (int j = this.size(); j < a.length; j++) {
                    a[j] = null;
                }
            }
            return a;
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    public E getElementAt(int position) {
        if (position < this.size()) {
            return array[position];
        } else {
            throw new NoSuchElementException("No such element in collection");
        }
    }

    private class MyIterator<E> implements Iterator<E> {
        int counter = 0;

        @Override
        public boolean hasNext() {
            return (counter < MyCollection.this.size());
        }

        @Override
        public E next() throws NoSuchElementException {
            if (this.hasNext()) {
                return (E) MyCollection.this.getElementAt(counter++);
            } else {
                throw new NoSuchElementException("No such element in collection");
            }
        }

        @Override
        public void remove() throws IllegalStateException {
            MyCollection.this.remove(MyCollection.this.getElementAt(--counter));
        }
    }
}
