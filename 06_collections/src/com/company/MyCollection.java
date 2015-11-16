package com.company;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * Custom collection that implements interface Collection
 * @author Sirotkin Mikhail
 * @param <E>
 */
public class MyCollection<E> implements Collection<E> {
    private int collectSize;
    private E[] myCollection;
    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public int size() {
        return collectSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    /**
     * This internal class implements Iterator interface and is used in custom collection class 'MyCollection'
     * @author Sirotkin Mikhail
     * @param <E>
     */
    private class MyIterator<E> implements Iterator<E>{
        /**
         * Field with locator of curent element
         */
        private int currentElement;
        private boolean nextMethodHasBeenCalled = false;

        /**
         * Constructor of MyIterator class.
         * Set currentElement value to start position - 0
         */
        public MyIterator(){
            currentElement = 0;
        }


        @Override
        public void remove() {
            if(!nextMethodHasBeenCalled){
                throw new IllegalStateException();
            }
            E[] newArr = new [5];
            //System.arraycopy(myCollection, currentElement, myCollection, currentElement - 1, collectSize - currentElement);
            //need to copy 2 parts: before and after currentElement to new array
            //then kill ald array and change it to new


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
