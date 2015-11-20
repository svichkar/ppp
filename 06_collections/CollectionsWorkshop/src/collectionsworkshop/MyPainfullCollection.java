/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionsworkshop;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author mednorcom
 */
public class MyPainfullCollection<E> implements Collection<E> {

    private E[] collcetionHolder;

    private E[] getCollcetionHolder() {
        return collcetionHolder;
    }

    private void setCollcetionHolder(E[] collcetionHandler) {
        this.collcetionHolder = collcetionHandler;
    }

    public MyPainfullCollection() {
        collcetionHolder = (E[]) (new Object[0]);
    }

    @Override
    public int size() {
        return this.getCollcetionHolder().length;

    }

    @Override
    public boolean isEmpty() {
        if (this.getCollcetionHolder().length > 0) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean contains(Object o) {
        for (Object collectionItem : this.getCollcetionHolder()) {
            if (collectionItem != null) {
                if (collectionItem.equals(o)) {
                    return true;
                }
            } else if (o == null) {
                return true;
            }
        }
        return false;

    }

    @Override
    public Iterator<E> iterator() {
        return new InterationWorker();
    }

    @Override
    public E[] toArray() {
        return (E[]) this.getCollcetionHolder().clone();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size()) // Make a new array of a's runtime type, but my contents:
        {
            return (T[]) Arrays.copyOf(this.getCollcetionHolder(), this.size(), a.getClass());
        }
        System.arraycopy(this.getCollcetionHolder(), 0, a, 0, this.size());
        if (a.length > this.size()) {
            a[this.size()] = null;
        }
        return (T[]) a;
    }

    @Override
    public boolean add(E e) {
        E[] updated = (E[]) new Object[this.size() + 1];
        System.arraycopy(this.getCollcetionHolder(), 0, updated, 0, this.size());
        updated[updated.length - 1] = e;
        this.setCollcetionHolder(updated);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Iterator iter = this.iterator();
        E[] updated = (E[]) new Object[this.size() - 1];
        int i = 0;
        boolean changed = false;
        while (iter.hasNext()) {
            E current = (E) iter.next();
            if (current == null || o == null) {
                if (current != o) {
                    updated[i] = current;
                    i++;
                    continue;
                }

            } else if (!current.equals(o)) {
                updated[i] = current;
                i++;
                continue;
            }
            changed = true;

        }
        if (changed) {
            this.setCollcetionHolder(updated);
            return true;
        } else {
            updated = null;
            return false;
        }

    }

    /**
     * Removes all given object instances for this collection
     *
     * @param o object to remove from this collection
     * @return true if one or more elements were removed
     */
    public boolean removeAll(E o) {
        Iterator iter = this.iterator();
        E[] updated = (E[]) new Object[this.size() - 1];
        int i = 0;
        while (iter.hasNext()) {
            E current = (E) iter.next();
            if (current == null || o == null) {
                if (current != o) {
                    updated[i] = current;
                    i++;
                }

            } else if (!current.equals(o)) {
                updated[i] = current;
                i++;
            }
        }
        if (i == this.size()) {
            updated = null;
            return false;
        } else {
            this.setCollcetionHolder(updated);
            return true;
        }

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int matches = 0;
        for (Object inputItem : c) {
            if (this.contains((E) inputItem)) {
                matches++;
            }
        }
        return matches == c.size();

    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean isChanged = false;
        for (Object inputItem : c) {
            this.add((E) inputItem);
            isChanged = true;
        }
        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        if (c == null) {
            return this.removeAll((E) c);
        }
        boolean arrayChanged = false;
        for (Object inputItem : c) {
            while (this.contains((E) inputItem)) {
                boolean elementChanged = this.remove((E) inputItem);
                if (!arrayChanged) {
                    arrayChanged = elementChanged;
                }
            }
        }
        return arrayChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean arrayChanged = false;
        for (E collectionItem : this.getCollcetionHolder()) {

            if (!c.contains(collectionItem)) {
                boolean elementChanged = this.removeAll(collectionItem);
                if (!arrayChanged) {
                    arrayChanged = elementChanged;
                }
            }
        }
        return arrayChanged;
    }

    @Override
    public void clear() {
        this.setCollcetionHolder((E[]) new Object[0]);
    }

    public class InterationWorker implements Iterator<E> {

        int current;

        public int getCurrent() {
            return current;
        }

        private void setCurrent(int current) {
            this.current = current;
        }

        private InterationWorker() {
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return size() > getCurrent();
        }

        @Override
        public E next() {
            if (current >= size()) {
                throw new NoSuchElementException();
            }
            this.setCurrent(this.getCurrent() + 1);
            return collcetionHolder[this.getCurrent() - 1];
        }

    }
}
