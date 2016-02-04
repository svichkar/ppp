package com.nixsolutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Created by pantiukhin on 2/3/2016.
 */
public class CollectionImplementation implements Collection {
    private Object[] objects = new Object[0];

    @Override
    public boolean add(Object obj) {
        objects = Arrays.copyOf(objects, size() + 1);
        objects[size() - 1] = obj;
        if (objects[size() - 1] == obj) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection collection) {
        boolean found = false;
        int initialSize = size();
        for (Object o : collection) {
            add(o);
        }
        for (Object o : objects) {
            if (!contains(o)) {
                found = false;
                break;
            } else {
                found = true;
            }
        }
        if (found && (size() == initialSize + collection.size())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        objects = new Object[0];
    }

    @Override
    public boolean contains(Object obj) {
        boolean contains = false;
        for (Object o : objects) {
            if (o.equals(obj)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public boolean containsAll(Collection collection) {
        boolean found = true;
        for (Object o : collection) {
            if (!contains(o)) {
                found = false;
                break;
            }
        }
        return found;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator iterator() {
        return new CollectionManipulationIterator();
    }

    @Override
    public boolean remove(Object obj) {

        Object[] temporary = new Object[size() - 1];
        int y = 0;
        for (int i = 0; i < size(); i++) {
            if (!objects[i].equals(obj)) {
                temporary[y] = objects[i];
                y++;
            }
        }
        objects = temporary;
        if (!contains(obj)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection collection) {
        for (Object o : collection) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection collection) {
        clear();
        objects = new Object[0];
        addAll(collection);
        return true;
    }

    @Override
    public int size() {
        return objects.length;
    }

    @Override
    public Object[] toArray() {
        return objects;
    }

    @Override
    public Object[] toArray(Object array[]) {
        objects = Arrays.copyOf(array, array.length);
        return objects;
    }

    class CollectionManipulationIterator implements Iterator {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex < objects.length) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object next() {
            if (currentIndex > objects.length) {
                throw new NoSuchElementException("Element with index " + currentIndex + "doesn't exist!");
            }
            Object nextElement = objects[currentIndex++];
            return nextElement;
        }
    }
}
