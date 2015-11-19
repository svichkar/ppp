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
 * Collection implementation with nullable elements
 *
 * @author mednorcom
 */
public class NullableCollection implements Collection {

    private Object[] collcetionHolder;

    private Object[] getCollcetionHolder() {
        return collcetionHolder;
    }

    private void setCollcetionHolder(Object[] collcetionHandler) {
        this.collcetionHolder = collcetionHandler;
    }

    public NullableCollection() {
        collcetionHolder = new Object[0];
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
    public Iterator iterator() {
        return new InterationWorker();
    }

    @Override
    public Object[] toArray() {
        return this.getCollcetionHolder().clone();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return this.getCollcetionHolder().clone();
    }

    @Override
    public boolean add(Object e) {
        Object[] updated = new Object[this.size() + 1];
        System.arraycopy(this.getCollcetionHolder(), 0, updated, 0, this.size());
        updated[updated.length - 1] = e;
        this.setCollcetionHolder(updated);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Iterator iter = this.iterator();
        Object[] updated = new Object[this.size() - 1];
        int i = 0;
        boolean changed = false;
        while (iter.hasNext()) {
            Object current = iter.next();
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
    public boolean removeAll(Object o) {
        Iterator iter = this.iterator();
        Object[] updated = new Object[this.size() - 1];
        int i = 0;
        while (iter.hasNext()) {
            Object current = iter.next();
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
    public boolean containsAll(Collection c) {
        int matches = 0;
        for (Object inputItem : c) {
            if (this.contains(inputItem)) {
                matches++;
            }
        }
        return matches == c.size();

    }

    @Override
    public boolean addAll(Collection c) {
        boolean isChanged = false;
        for (Object inputItem : c) {
            this.add(inputItem);
            isChanged = true;
        }
        return isChanged;
    }

    @Override
    public boolean removeAll(Collection c) {

        if (c == null) {
            return this.removeAll((Object) c);
        }
        boolean arrayChanged = false;
        for (Object inputItem : c) {
            while (this.contains(inputItem)) {
                boolean elementChanged = this.remove(inputItem);
                if (!arrayChanged) {
                    arrayChanged = elementChanged;
                }
            }
        }
        return arrayChanged;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean arrayChanged = false;
        for (Object collectionItem : this.getCollcetionHolder()) {

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
        this.setCollcetionHolder(new Object[0]);
    }

    public class InterationWorker implements Iterator<Object> {

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
        public Object next() {
            if (current >= size()) {
                throw new NoSuchElementException();
            }
            this.setCurrent(this.getCurrent() + 1);
            return getCollcetionHolder()[this.getCurrent() - 1];
        }
    }
}
