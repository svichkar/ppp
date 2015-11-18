/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionsworkshop;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author mednorcom
 */
public class MySleeplessCollection implements Collection {

    private Object[] collcetionHolder;

    private Object[] getCollcetionHolder() {
        return collcetionHolder;
    }

    private void setCollcetionHolder(Object[] collcetionHandler) {
        this.collcetionHolder = collcetionHandler;
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
        for (Object collectionItem : collcetionHolder) {
            if (collectionItem.equals(o)) {
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
        //if (a.length < this.size()) {
        return a = a.getClass().cast(this.getCollcetionHolder().clone());
        // }
        //else
        //{
        //    return a.getClass().cast(this.getCollcetionHolder().clone());
        //}
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
        for (int i = 0; i < this.size(); i++) {
            if (this.getCollcetionHolder()[i].equals(o)) {
                Object[] updated = new Object[this.size() - 1];
                updated = Arrays.copyOf(this.getCollcetionHolder(), i - 1);
                System.arraycopy(this.getCollcetionHolder(), i + 1, updated, i, this.size() - i);
                this.setCollcetionHolder(updated);
                return true;
            }
        }
        return false;
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

        boolean arrayChanged = false;
        for (Object inputItem : c) {
            boolean elementChanged = this.remove(inputItem);
            if (!arrayChanged) {
                arrayChanged = elementChanged;
            }
        }
        return arrayChanged;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean arrayChanged = false;
        for (Object collectionItem : getCollcetionHolder()) {

            if (!c.contains(collectionItem)) {
                boolean elementChanged = this.remove(collectionItem);
                if (!arrayChanged) {
                    arrayChanged = elementChanged;
                }
            }
        }
        return arrayChanged;
    }

    @Override
    public void clear() {
        this.setCollcetionHolder(null);
    }

    public class InterationWorker implements Iterator<Object> {

        int current;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public InterationWorker() {
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return size() < getCurrent();
        }

        @Override
        public Object next() {
            this.setCurrent(this.getCurrent() + 1);
            return getCollcetionHolder()[this.getCurrent()];
        }
    }
}
