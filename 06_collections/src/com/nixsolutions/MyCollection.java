package com.nixsolutions;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * Custom collection that implements interface Collection
 * @author Sirotkin Mikhail
 * @param <E>
 */
public class MyCollection<E> implements Collection<E> {
    private int collectSize;
    private E[] myCollection;
    /**
     * Constructor of MyCollection
     */
    public MyCollection() {
        myCollection = (E[]) new Object[]{};
        collectSize = 0;
    }

    /**
     * @return the number of elements in this collection
     */
    @Override
    public int size() {
        return collectSize;
    }

    /**
     * @return true if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return !(collectSize > 0);
    }

    /**
     * Check if current collection contains element that equals to input object
     * @param o is object for comparing
     * @return true if this collection contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < collectSize; i++) {
            if (myCollection[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Iterator of collection
     * @return an Iterator over the elements in this collection
     */
    @Override
    public MyIterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * Returns an array containing all of the elements in this collection
     * @return new array with all elements of current collection
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(myCollection, collectSize);
    }

    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is
     * that of the specified array.
     * @param a - the array into which the elements of this collection are to be stored, if it is big enough;
     *             otherwise, a new array of the same runtime type is allocated for this purpose.
     * @param <T> - type of objects in returnable array
     * @exception NullPointerException if param a == null
     * @return an array containing all of the elements in this collection with expected runtime type
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if(a == null){
            throw new NullPointerException();
        }
        if (a.length < collectSize) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), collectSize);
        } else if (a.length > collectSize) {
            a[collectSize] = null;
        }
        System.arraycopy(myCollection, 0, a, 0, collectSize);
        return a;
    }

    /**
     * Method increments collection size and adds new element to the end of current collection
     * @param e - element that we have to add to the end of this collection
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean add(E e) {
        collectSize++;
        myCollection = Arrays.copyOf(myCollection, collectSize);
        myCollection[collectSize-1] = e;
        return true;
    }

    /**
     * Method removes a single instance of the specified element from this collection, if it is present
     * @param o - element that we want to remove
     * @return true if an element was removed as a result of this call
     */
    @Override
    public boolean remove(Object o) {
        MyIterator iter = this.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (o.equals(obj)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Method returns true if this collection contains all of the elements in the specified collection.
     * @param c - collection with expected elements
     * @see this.contains (Object o)
     * @return true if this collection contains all of the elements in the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem : c) {
            if (!this.contains(elem)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method adds elements of input collection to current collection
     * @param c - input collection that we want to add
     * @exception NullPointerException if param c == null
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c == null){
            throw new NullPointerException();
        }
        myCollection = Arrays.copyOf(myCollection, (collectSize + c.size()));
        System.arraycopy(c.toArray(), 0, myCollection, collectSize, c.toArray().length);
        collectSize += c.toArray().length;
        return true;
    }

    /**
     * Method removes all of this collection's elements that are also contained in the specified collection
     * @param c - collection with elements that we want to remove
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        int startSize = collectSize;
        Object[] toRemove = c.toArray();
        for(int i =0; i < toRemove.length; i++) {
            boolean ready = false;
            while(!ready){
                ready = !this.remove(toRemove[i]);
            }
        }
        return startSize != collectSize;
    }

    /**
     * Method saves in current collection only elements from other collection
     * @param c - specified collection
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        int startSize = collectSize;
        MyCollection<Object> tempArray = new MyCollection<Object>();
        for(int i = 0; i < collectSize; i++){
            if(!c.contains(myCollection[i])) {
                tempArray.add(myCollection[i]);
            }
        }
        this.removeAll(tempArray);
        return startSize != collectSize;
    }

    /**
     * Method removes all of the elements from this collection
     */
    @Override
    public void clear() {
        MyIterator iter = new MyIterator();
        while(iter.hasNext()){
            iter.remove();
        }
    }

    /**
     * This internal class implements Iterator interface and is used in custom collection class 'MyCollection'
     * @author Sirotkin Mikhail
     * @param <E>
     */
    private class MyIterator<E> implements Iterator<E>{
        /**
         * Field with locator of current element
         */
        private int currentElement;
        private boolean nextMethodHasBeenCalled;

        /**
         * Constructor of MyIterator class.
         * Set currentElement value to start position 0
         */
        public MyIterator(){
            currentElement = 0;
            nextMethodHasBeenCalled = false;
        }

        /**
         * Method removes current element from collection
         * This method can be called only once per call to next()
         * @exception IllegalStateException() if nextMethodHasBeenCalled is false
         */
        @Override
        public void remove() {
            if(!nextMethodHasBeenCalled){
                throw new IllegalStateException();
            }
            if (collectSize - currentElement > 0) {
                System.arraycopy(myCollection, currentElement, myCollection, currentElement - 1, collectSize - currentElement);
            }
            myCollection[--collectSize] = null;
            nextMethodHasBeenCalled = false;
        }

        /**
         * Show if current element is a lasr element of collection
         * @return false if current element is last element in collection. Return true in other states
         */
        @Override
        public boolean hasNext() {
            return currentElement < (collectSize - 1);
        }

        /**
         * Method that return next element of current collection
         * @return next element of current collection
         * @throws NoSuchElementException if current element is last
         */
        @Override
        public E next() {
            nextMethodHasBeenCalled = true;
            if(hasNext()){
                return (E) myCollection[currentElement++];
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }
}
